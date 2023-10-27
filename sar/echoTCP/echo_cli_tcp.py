#!/usr/bin/env python3
import socket, sys 
PORT = 50007 # Comprueba que se ha pasado un argumento. 
if len( sys.argv ) != 2: 
    print( "Uso: {} <servidor>".format( sys.argv[0] ) ) 
    exit( 1 )

"""A COMPLETAR POR EL/LA ESTUDIANTE: Crear un socket y enviar peticion de conexion al servidor. """ 
s= socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.connect(('', PORT))
print( "Introduce el mensaje que quieres enviar (mensaje vacío para terminar):" ) 
mensajeEntero = ""
while True:
    mensaje = input() 
    if not mensaje: 
        break
    """A COMPLETAR POR EL/LA ESTUDIANTE: Enviar mensaje y recibir 'eco'. Mostrar en pantalla lo recibido. ¡Cuidado! Recuerda que no hay garantías de recibir el mensaje completo en una única lectura. """ 
    mensajeEntero+=mensaje
    s.sendall(mensajeEntero.encode())
    print(s.recv(1024))

s.close()
