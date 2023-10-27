#!/usr/bin/env python3 
import socket, signal, os

PORT = 50007 

"""A COMPLETAR POR EL/LA ESTUDIANTE: Crear un socket, asignarle su dirección y convertirlo en socket de escucha. """ 
s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('', PORT))
s.listen(5)

signal.signal(signal.SIGCHLD, signal.SIG_IGN)
mensaje=""
while True: 
    """A COMPLETAR POR EL/LA ESTUDIANTE: Aceptar peticion de conexion. Mientras el cliente no cierre la conexion, recibir un mensaje y responder con el mismo. Cerrar conexión. """ 
    dialogo, _ = s.accept()
    if os.fork():
        dialogo.close()
    else:
        s.close()
        while True:
            mensaje=dialogo.recv(24)
            print(mensaje)
            if not mensaje:
                break
            dialogo.sendall(mensaje)
        dialogo.close()
        os._exit(0)
"""A COMPLETAR POR EL/LA ESTUDIANTE: Cerrar socket de escucha. """
s.close()
