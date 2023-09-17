import socket, time

s = socket.socket(socket.AF_INET, socket.SOCK_DGRAM)

s.bind(('', 50013))
# print(s.bind(('', 50013)))
print(s)

while True:
    mensaje_cliente, dir_cli = s.recvfrom(1024)
    __import__('pprint').pprint(mensaje_cliente)
    mensaje = time.ctime()
    s.sendto(mensaje.encode(), dir_cli)
s.close()
