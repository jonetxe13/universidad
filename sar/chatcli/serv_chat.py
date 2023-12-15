#!/usr/bin/env python3

from twisted.internet import protocol
from twisted.protocols.basic import LineReceiver
from twisted.internet.protocol import Factory
from twisted.internet import reactor

MAX_USERS = 100
MAX_MSG_LENGTH = 255
MAX_USER_LENGTH = 16
PORT = 8000

class ChatProtocol(LineReceiver):
    def __init__(self, factory):
        self.factory = factory
        self.name = None

    def connectionMade(self):
        # Enviar mensaje de bienvenida y lista de usuarios actuales
        valores = self.factory.features.values()
        self.sendLine("FTR{}".format(" ".join(valores)).encode())
        self.sendUserList()
        self.resetInactivityTimer()

    def connectionLost(self, reason):
        # Usuario desconectado, notificar a otros usuarios
        if self.name in self.factory.users:
            del self.factory.users[self.name]
            self.broadcast(b"OUT" + self.name.encode()

    def resetInactivityTimer(self):
        # Cancelar el temporizador existente, si hay uno
        if self.inactivity_call is not None and self.inactivity_call.active():
            self.inactivity_call.cancel()

        # Configurar un nuevo temporizador
        self.inactivity_call = reactor.callLater(self.inactivity_timeout, self.handleInactivity)

    def handleInactivity(self):
        # Manejar la inactividad enviando el comando "NOP" al cliente
        if self.name is not None:
            message = b"NOP"
            self.sendLine(message)
        self.resetInactivityTimer()

    def lineReceived(self, line):
        # Procesar comando recibido
        command = line[0:3]
        print(command)
        rest = line[3::]
        print(rest)
        parts = []
        parts.append(command)
        parts.append(rest)
        print(parts)

        if command == b"NME":
            self.handleNME(parts)
        elif command == b"MSG":
            self.handleMSG(parts)
        elif command == b"WRT":
            self.handleWRT(parts)
        self.resetInactivityTimer()


    def handleNME(self, parts):
        # Procesar comando NME (nombre de usuario)
        if self.name is not None:
            # Ya tiene un nombre asignado, enviar error
            self.sendError(b"4")
            return
        print(parts)
        new_name = parts[1].decode("utf-8")

        if not self.isNameValid(new_name):
            # Nombre no válido, enviar error
            self.sendError(b"2")
            return

        if len(new_name) > MAX_USER_LENGTH:
            # Nombre demasiado largo, enviar error
            self.sendError(b"3")
            return

        if new_name in self.factory.users:
            # Nombre ya en uso, enviar error
            self.sendError(b"4")
            return

        # Nombre válido, asignar y notificar a otros usuarios
        self.name = new_name
        self.factory.users[self.name] = self
        self.broadcast(b"INN" + self.name.encode())

    def handleMSG(self, parts):
        # Procesar comando MSG (enviar mensaje)
        if self.name is None:
            # Usuario no tiene nombre, enviar error
            self.sendError(b"0")
            return

        message = parts[1].decode("utf-8")

        if len(message) > MAX_MSG_LENGTH:
            # Mensaje demasiado largo, enviar error
            self.sendError(b"5")
            return

        with open("palabras_censuradas.txt", 'r') as file:
            palabras_prohibidas = file.read().splitlines()

        palabras_mensaje = message.split(' ')
        for i in range(len(palabras_mensaje)):
            palabra = palabras_mensaje[i]
            if palabra in palabras_prohibidas:
                palabra_censurada = '#' * len(palabra)
                palabras_mensaje[i] = palabra_censurada

        message = ' '.join(palabras_mensaje)
        print("message is: {}".format(parts))

        # Enviar mensaje a otros usuarios
        self.broadcast(b"MSG" + self.name.encode() + b" " + message.encode())
    
    def handleWRT(self, parts):
        # Procesar comando MSG (enviar mensaje)
        if self.name is None:
            # Usuario no tiene nombre, enviar error
            self.sendError(b"0")
            return

        # Usuario no tiene nombre, enviar error
        # Enviar mensaje a otros usuarios
        print("el nombre de usuario de WRT es: {}".format(self.name))
        self.broadcast(b"WRT" + self.name.encode())

    def sendUserList(self):
        # Enviar lista de usuarios a este cliente
        user_list = b"USR " + " ".join(self.factory.users.keys()).encode()
        self.sendLine(user_list)

    def sendError(self, error_code):
        # Enviar mensaje de error al cliente
        self.sendLine(b"-" + error_code)

    def broadcast(self, message):

        # Enviar mensaje a todos los usuarios
        for user in self.factory.users.values():
            if user != self:
                print("se envia el mensaje {}".format(message))
                user.sendLine(message)

    def isNameValid(self, name):
        # Validar que el nombre solo contiene caracteres permitidos
        # Puedes personalizar esta función según tus necesidades
        return all(c.isalnum() or c in "_-" for c in name)

class ChatFactory(Factory):
    def __init__(self):
        self.users = {}
        self.features = { 'FILES':'0' , 'CEN':'1', 'NOP':'1', 'SSL':'0' }

    def buildProtocol(self, addr):
        return ChatProtocol(self)

if __name__ == "__main__":
    reactor.listenTCP(PORT, ChatFactory())
    reactor.run()
