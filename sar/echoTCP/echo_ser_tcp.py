#!/usr/bin/env python3 
import socket 

PORT = 50007 

"""A COMPLETAR POR EL/LA ESTUDIANTE: Crear un socket, asignarle su dirección y convertirlo en socket de escucha. """ 
s=socket.socket(socket.AF_INET, socket.SOCK_STREAM)
s.bind(('', PORT))
s.listen(5)

mensaje=""
while True: 
    """A COMPLETAR POR EL/LA ESTUDIANTE: Aceptar peticion de conexion. Mientras el cliente no cierre la conexion, recibir un mensaje y responder con el mismo. Cerrar conexión. """ 
    dialogo, _ = s.accept()
    if not dialogo:
        break
    mensaje+=dialogo.recv(24).decode()
    print(mensaje)
    dialogo.sendall(mensaje.encode())
    dialogo.close()

"""A COMPLETAR POR EL/LA ESTUDIANTE: Cerrar socket de escucha. """
s.close()
