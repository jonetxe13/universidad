#!/usr/bin/env python3
import socket, sys
PORT = 50007

if len( sys.argv ) > 2:
    print( "Uso: {} <servidor>".format( sys.argv[0] ) )
    exit( 1 )
# Comprueba que se ha pasado un argumento.
"""A COMPLETAR POR EL/LA ESTUDIANTE:
Crear el socket.
"""

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
if len( sys.argv ) == 1:
    dir_serv = input("introduce la direccion con la que desea comunicarse: ")
else:
    dir_serv = sys.argv[1]
if not dir_serv:
   print("no has introducido una direccion valida, saliendo...") 
   exit(1)

print( "Introduce el mensaje que quieres enviar (mensaje vacío para terminar):" )
while True:

    mensaje = input()
    buf = b''

    """A COMPLETAR POR EL/LA ESTUDIANTE:
    Enviar mensaje y recibir 'eco'.
    Mostrar en pantalla lo recibido.
    """

    print("longitud de mensaje en caracteres: ", len(mensaje))
    if sys.getsizeof(mensaje) > 32:
        size = sys.getsizeof(mensaje)  
        mensaje_trozos = mensaje
        while size >= 0 or mensaje_trozos:
            s.sendto(mensaje_trozos[len(mensaje_trozos)-32:len(mensaje_trozos)].encode(), (dir_serv, PORT))
            buf=s.recv(1024)
            mensaje_trozos = mensaje_trozos[0:len(mensaje_trozos)-32];
            size -= 32
    else:
        s.sendto(mensaje.encode(), (dir_serv, PORT))
        buf=s.recv(1024)
    print("\nlongitud de mensaje en bytes: ", sys.getsizeof(mensaje))
    print(dir_serv)

    print("respuesta del servidor: ", buf.decode())
    print("datos del servidor", s.getsockname())
    if not buf:
        break

"""A COMPLETAR POR EL/LA ESTUDIANTE:
Cerrar socket.
"""
s.close()
