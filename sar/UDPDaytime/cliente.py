import socket, sys

dir_serv = (sys.argv[1], 50013)

s = socket.socket( socket.AF_INET, socket.SOCK_DGRAM )

mensaje = b""

s.sendto( mensaje, dir_serv )

buf = s.recv( 1024 )
print( "Datos recibidos del servidor:", buf.decode() )

s.close()
