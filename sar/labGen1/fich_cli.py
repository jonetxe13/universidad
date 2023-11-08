#!/usr/bin/env python3

import socket
import sys
import os
import szasar

SERVER = 'localhost'
PORT = 50000
ER_MSG = (
    "Correcto.",
    "Comando desconocido o inesperado.",
    "Usuario desconocido.",
    "Clave de paso o password incorrecto.",
    "Error al crear la lista de ficheros.",
    "El fichero no existe.",
    "Error al bajar el fichero.",
    "Un usuario anonimo no tiene permisos para esta operacion.",
    "Error al borrar el fichero.")


class Menu:
    List, Download, Delete, Upload, Exit = range(1, 6)
    Options = ("Lista de ficheros", "Bajar fichero",
               "Borrar fichero", "Subir fichero", "Salir")

    def menu():
        print("+{}+".format('-' * 30))
        for i, option in enumerate(Menu.Options, 1):
            print("| {}.- {:<25}|".format(i, option))
        print("+{}+".format('-' * 30))

        while True:
            try:
                selected = int(input("Selecciona una opción: "))
            except:
                print("Opción no válida.")
                continue
            if 0 < selected <= len(Menu.Options):
                return selected
            else:
                print("Opción no válida.")


def iserror(message):
    if (message.startswith("ER")):
        code = int(message[2:])
        print(ER_MSG[code])
        return True
    else:
        return False


def int2bytes(n):
    if n < 1 << 10:
        return str(n) + " B  "
    elif n < 1 << 20:
        return str(round(n / (1 << 10))) + " KiB"
    elif n < 1 << 30:
        return str(round(n / (1 << 20))) + " MiB"
    else:
        return str(round(n / (1 << 30))) + " GiB"


if __name__ == "__main__":
    if len(sys.argv) > 3:
        print("Uso: {} [<servidor> [<puerto>]]".format(sys.argv[0]))
        exit(2)

    if len(sys.argv) >= 2:
        SERVER = sys.argv[1]
    if len(sys.argv) == 3:
        PORT = int(sys.argv[2])

    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect((SERVER, PORT))

    while True:
        user = input("Introduce el nombre de usuario: ")
        message = "{}{}\r\n".format(szasar.Command.User, user)
        s.sendall(message.encode("ascii"))
        message = szasar.recvline(s).decode("ascii")
        if iserror(message):
            continue

        password = input("Introduce la contraseña: ")
        message = "{}{}\r\n".format(szasar.Command.Password, password)
        s.sendall(message.encode("ascii"))
        message = szasar.recvline(s).decode("ascii")
        if not iserror(message):
            break

    while True:
        option = Menu.menu()

        if option == Menu.List:
            message = "{}\r\n".format(szasar.Command.List)
            s.sendall(message.encode("ascii"))
            message = szasar.recvline(s).decode("ascii")
            if iserror(message):
                continue
            filecount = 0
            print("Listado de ficheros disponibles")
            print("-------------------------------")
            while True:
                line = szasar.recvline(s).decode("ascii")
                if line:
                    filecount += 1
                    fileinfo = line.split('?')
                    print("{:<20} {:>8}".format(fileinfo[0], int2bytes(int(fileinfo[1]))))
                else:
                    break
            print("-------------------------------")
            if filecount == 0:
                print("No hay ficheros disponibles.")
            else:
                plural = "s" if filecount > 1 else ""
                print("{0} fichero{1} disponible{1}.".format(filecount, plural))

        elif option == Menu.Download:
            filename = input("Indica el fichero que quieres bajar: ")
            message = "{}{}\r\n".format(szasar.Command.Download, filename)
            s.sendall(message.encode("ascii"))
            message = szasar.recvline(s).decode("ascii")
            if iserror(message):
                continue
            filesize = int(message[2:])
            message = "{}\r\n".format(szasar.Command.Download2)
            s.sendall(message.encode("ascii"))
            message = szasar.recvline(s).decode("ascii")
            if iserror(message):
                continue
            filedata = szasar.recvall(s, filesize)
            try:
                with open(filename, "wb") as f:
                    f.write(filedata)
            except:
                print("No se ha podido guardar el fichero en disco.")
            else:
                print("El fichero {} se ha descargado correctamente.".format(filename))

        elif option == Menu.Delete:
            filename = input("Indica el fichero que quieres borrar: ")
            message = "{}{}\r\n".format(szasar.Command.Delete, filename)
            s.sendall(message.encode("ascii"))
            message = szasar.recvline(s).decode("ascii")
            if not iserror(message):
                print("El fichero {} se ha borrado correctamente.".format(filename))

        elif option == Menu.Upload:
            filename = input("Indica el fichero que quieres enviar: ")
            try:
                with open(filename, "rb") as f:
                    filedata = f.read()
                    filesize = len(filedata)  # Obtener el tamaño del archivo
            except FileNotFoundError:
                print("El archivo no se ha encontrado.")
                continue
            except Exception as e:
                print("Error al leer el archivo:", e)
                continue

            # Enviar el comando de carga con el nombre del archivo y su tamaño al servidor
            message = "{}{}?{}\r\n".format(szasar.Command.Upload, filename, filesize)
            print("el message es: {}", message)
            s.sendall(message.encode("ascii"))
            response = szasar.recvline(s).decode("ascii")
            print("el respuesta es: {}", response)

            if iserror(response):
                continue

            # Enviar los datos del archivo al servidor
            s.sendall(filedata)
            print("el filedata es: {}", filedata)

            # Confirmar el final del archivo enviando un salto de línea
            # s.send('\r\n'.encode("ascii"))

            response = szasar.recvline(s).decode("ascii") #################### error aquiii
            print("la ultima response es: {}", response)
            if iserror(response):
                print("Error al enviar el archivo al servidor.")
            else:
                print("El archivo {} se ha enviado correctamente al servidor.".format(filename))


        elif option == Menu.Exit:
            message = "{}\r\n".format( szasar.Command.Exit )
            s.sendall( message.encode( "ascii" ) )
            message = szasar.recvline( s ).decode( "ascii" )
            break
    s.close()
