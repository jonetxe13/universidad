import socket, sys

dir_serv = (sys.argv[1], 50013)

s = socket.socket( socket.AF_INET, socket.SOCK_DGRAM )

mensaje = b""

a = b"Es un virus gallego, borra todos tus datos"
s.sendto( a, dir_serv )

buf = s.recv( 1024 )
print( "Datos recibidos del servidor:", buf.decode() )

s.close()

