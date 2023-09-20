#!/usr/bin/env python3

import socket, sys

PORT = 50007

"""A COMPLETAR POR EL/LA ESTUDIANTE:
Crear un socket y asignarle su direccion.
"""
s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
s.bind(('',0))
print(s)

while True:
    """A COMPLETAR POR EL/LA ESTUDIANTE:
    Recibir un mensaje y responder con el mismo.
    """
    recibido, dir_cliente = s.recvfrom(1024) #con recvfrom te da la dir_ip, con recv no

    print(recibido)
    print("datos del servidor (ip, puerto): ", dir_cliente)

    s.sendto(recibido, dir_cliente)
    break
"""A COMPLETAR POR EL/LA ESTUDIANTE:
Cerrar socket.
"""
s.close()
