#!/usr/bin/env python3

import sys
import socket
import time

NULL = b'\x00'
RRQ = b'\x00\x01'
WRQ = b'\x00\x02'
DATA = b'\x00\x03'
ACK = b'\x00\x04'
ERROR = b'\x00\x05'

PORT = 50069
BLOCK_SIZE = 512

def get_file(s, serv_addr, filename):
    start = time.time()
    f = open(filename, 'wb')
    """A COMPLETAR POR EL/LA ESTUDIANTE:
    Enviar al servidor la petición de descarga de fichero (RRQ)
    """
    request = RRQ + filename.encode() + NULL + b'octet' + NULL
    s.sendto(request, serv_addr)

    expected_block = 1

    while True:
        """A COMPLETAR POR EL/LA ESTUDIANTE:
        Recibir respuesta del servidor y comprobar que tiene el código
        correcto (DATA), si no, terminar.
        Comprobar que el número de bloque es el correcto (expected_block),
        si no, volver al comienzo del bucle.
        Escribir en el fichero (f) el bloque de datos recibido.
        Responder al servidor con un ACK y el número de bloque
        correspondiente.
        Si el tamaño del bloque de datos es menor que BLOCK_SIZE es el
        último, por tanto, salir del bucle.
        Si no, incrementar en uno el número de bloque esperado
        (expected_block)
        """
        data, server = s.recvfrom(4 + BLOCK_SIZE)
        if data[:2] != DATA:
            print('Recivido un paquete random, terminar')
            break

        received_block = int.from_bytes(data[2:4], byteorder='big')
        if received_block != expected_block:
            print('bloque fuera de orden, ignorar')
            continue
        f.write(data[4:])

        ack_packet = ACK + data[2:4]
        s.sendto(ack_packet, server)
        
        if len(data) < BLOCK_SIZE+4:
            break
        
        expected_block += 1


    f.close()
    elapsed = time.time() - start
    bytes_received = (expected_block - 1) * BLOCK_SIZE
    print('{} bytes received in {:.2e} seconds ({:.2e}b/s).'.format(bytes_received, elapsed, bytes_received * 8 / elapsed))

if __name__ == '__main__':
    if len(sys.argv) != 3:
        print('Usage: {} server filename'.format(sys.argv[0]))
        exit(1)

    server_address = (sys.argv[1], PORT)
    file_to_receive = sys.argv[2]

    s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)
    get_file(s, server_address, file_to_receive)

