#!/usr/bin/env python3
import socket, sys
import getpass

# Configuración servidor POP local de la asignatura SAR
SERV_POP = 'dif-mail.ehu.es'
PORT_POP = 2110  # Puerto POP3 estándar

# Definir mensajes de error
ER_MSG = {
    1: "Error de autenticación",
    # Agrega más mensajes de error según sea necesario
}

def recvline(s, removeEOL=True):
    line = b''
    CRreceived = False
    while True:
        c = s.recv(1)
        if c == b'':
            raise EOFError("Connection closed by the peer before receiving an EOL.")
        line += c
        if c == b'\r':
            CRreceived = True
        elif c == b'\n' and CRreceived:
            if removeEOL:
                return line[:-2]
            else:
                return line
        else:
            CRreceived = False

def recvmultiline(s, removeEOL=True):
    message = []
    mensaje_nuevo = b''
    while mensaje_nuevo != b'.':
        mensaje_nuevo = recvline(s)
        # print(mensaje_nuevo.decode())
        message.append(mensaje_nuevo.decode())
    return message

def iserror(message):
    if message.startswith("ER"):
        code = int(message[2:])
        print(ER_MSG[code])
        return True
    else:
        return False

def int2bytes(n):
    if n < 1 << 10:
        return str(n) + " B "
    elif n < 1 << 20:
        return str(round(n / (1 << 10))) + " KiB"
    elif n < 1 << 30:
        return str(round(n / (1 << 20))) + " MiB"
    else:
        return str(round(n / (1 << 30))) + " GiB"

if __name__ == "__main__":
    if len(sys.argv) != 1:
        print("Uso: python3 {}".format(sys.argv[0]))
        exit(1)

    # Establecer conexión con el servidor POP3
    serv_pop = (SERV_POP, PORT_POP)
    s = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    s.connect(serv_pop)
    response = recvline(s)
    if not response.startswith(b"+OK"):
        print("Error al conectar con el servidor POP3.")
        exit(1)

    # Autenticación en el estado de autorización
    CUENTA = input("Introduce tu cuenta asociada al correo del servidor local: ")
    CLAVE = getpass.getpass()
    s.sendall(f"USER {CUENTA}\r\n".encode())
    response = recvline(s)
    if not response.startswith(b"+OK"):
        print("Error al enviar el nombre de usuario.")
        exit(1)

    s.sendall(f"PASS {CLAVE}\r\n".encode())
    response = recvline(s)
    if not response.startswith(b"+OK"):
        print("Error de autenticación.")
        exit(1)
    
    #Lista de asignaturas
    lasign = ['SAR', 'AC', 'ISO']
    # Lista de contadores
    lcont = dict()
    for asign in lasign:
        lcont[ asign ] = 0
    # Obtener la lista de mensajes
    s.sendall(b"STAT\r\n")
    response = recvline(s)
    # print(f"respuesta 1: {response}")
    if response.startswith(b"+OK"):
        _, num_mensajes, _ = response.split()
        # print(f"respuesta 2, num de mensajes: {int(num_mensajes)}")
        # Obtener el asunto del mensaje
        for number in range(1,int(num_mensajes)):
            s.sendall(f"TOP {number} 0\r\n".encode())
            response = recvmultiline(s)
            # print(f"el response es: {response}")
            if response[0].startswith("+OK"):
                subject_line = response[14]
                print(subject_line)
                # Aquí deberías analizar el asunto para determinar la asignatura
                for asignatura in lasign:
                    if asignatura.lower() in subject_line.lower():
                        print(f"Mensaje {number} - Asignatura: {asignatura}")
                        break
            else:
                print(f"No se pudo obtener el asunto para el mensaje {number}")

    # Cerrar sesión de usuario POP3
    s.sendall(b"QUIT\r\n")
    response = recvline(s)
    if response.startswith(b"+OK"):
        print("Sesión cerrada correctamente.")
    else:
        print("Error al cerrar la sesión.")

    # Cerrar conexión con el servidor POP3
    s.close()

