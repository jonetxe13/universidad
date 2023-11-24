#!/usr/bin/env python3

import socket, sys, time
import getpass
import ssl

"""A COMPLETAR POR EL/LA ESTUDIANTE:
# Cambiar según configuración servidor POP en cliente de correo UPV/EHU
"""
SERV_POP = "ikasle.ehu.eus"
PORT_POP = 995

"""A COMPLETAR POR EL/LA ESTUDIANTE:
# Cambiar según configuración servidor SMTP en cliente de correo UPV/EHU
"""
SERV_SMTP = "smtp.ehu.eus"
PORT_SMTP = 25

class ComPOP3:
    Capa, User, Pass, Stat, Top, Quit = ("CAPA", "USER", "PASS", "STAT", "TOP", "QUIT")

class ComSMTP:
    Helo, Ehlo, STLS, From, To, Data, Quit = ("HELO", "EHLO", "STARTTLS", "MAIL FROM:", "RCPT TO:", "DATA", "QUIT")

# Código OK (respuesta positiva) a cada comando SMTP
CodOKSMTP = dict()
CodOKSMTP[ "connect" ] = CodOKSMTP[ ComSMTP.STLS ] = "220"
CodOKSMTP[ ComSMTP.Helo ] = CodOKSMTP[ ComSMTP.Ehlo ] = CodOKSMTP[ ComSMTP.From ] = CodOKSMTP[ ComSMTP.To ] = "250"
CodOKSMTP[ ComSMTP.Data ] = "354"
CodOKSMTP[ ComSMTP.Quit ] = "221"

# Campos 'Internet Message Format' (IMF) [RFC5322]
class FieldIMF:
    Date, From, To, Subject = ("Date:", "From:", "To:", "Subject:")

def recvline( s, removeEOL = True ):
    line = b''
    CRreceived = False
    while True:
        c = s.recv( 1 )
        if c == b'':
            raise EOFError( "Connection closed by the peer before receiving an EOL." )
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

def recvmultiline( s ):
    msg = recvline( s ).decode( "ascii" )
    if isPOPerror( msg ):
        exit( 1 )
    mline = []
    while msg != ".":
        try:
            msg = recvline( s ).decode( "ascii" )
        except Exception as e:
            print( "Error: {}".format( e ) )
            continue
        else:
            if msg != ".":
                mline.append( msg )
    return mline

def isPOPerror( msg ):
    if msg.startswith( "-ERR" ):
        print( "ERROR! {}".format( msg[5:] ))
        return True
    else:
        return False

def recvEHLOmultiline( s ):
    msg = recvline( s ).decode( "ascii" )
    if isSMTPerror( msg, ComSMTP.Ehlo ):
        exit( 1 )
    print( msg )
    mline = []
    while msg.startswith( "250" ) and not msg.startswith( "250 " ):
        try:
            msg = recvline( s ).decode( "ascii" )
        except Exception as e:
            print( "Error: {}".format( e ) )
            #input( "RETURN sakatu!" )
            continue
        else:
            if msg.startswith( "250" ):
                mline.append( msg )
            else:
                print( "Error: {}".format( msg ) )
    return mline

def isSMTPerror( msg, comando = "connect" ):
    if not msg.startswith( CodOKSMTP[ comando ] ):
        print( "ERROR! {}".format( msg ))
        return True
    else:
        return False

def int2bytes( n ):
    if n < 1 << 10:
        return str(n) + " B  "
    elif n < 1 << 20:
        return str(round( n / (1 << 10) ) ) + " KiB"
    elif n < 1 << 30:
        return str(round( n / (1 << 20) ) ) + " MiB"
    else:
        return str(round( n / (1 << 30) ) ) + " GiB"

# Programa principal
if __name__ == "__main__":
    if len( sys.argv ) != 1:
        print( "Uso: python3 {}".format( sys.argv[0] ) )
        exit( 1 )

    #############################################
    # Analizar buźon usuario POP3 servidor seguro
    #############################################
    serv_pop = (SERV_POP, PORT_POP)
    context = ssl.create_default_context()
    s = socket.socket( socket.AF_INET, socket.SOCK_STREAM )
    ssl_sock = context.wrap_socket( s, server_hostname=SERV_POP)

    # s = socket.socket( socket.AF_INET, socket.SOCK_STREAM )

    """A COMPLETAR POR EL/LA ESTUDIANTE:
    Conexión segura con el servidor POP3
    """
    ssl_sock.connect( serv_pop )

    # Saludo del servidor POP3
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isPOPerror( msg ):
        exit( 1 )

    """A COMPLETAR POR EL/LA ESTUDIANTE:
    Certificado del servidor POP3
    """
    cert=ssl_sock.getpeercert()
    # print(cert)
    ssl.match_hostname(cert, SERV_POP)

    """AVISO: A REALIZAR POR EL/LA ESTUDIANTE:
    Asegurate que todas las comunicaciones que se hacían con el socket
    ahora se hagan con un socket seguro
    """

    # Capacidades servidor POP3 (Opcional)
    msg = "{}\r\n".format( ComPOP3.Capa )
    ssl_sock.sendall( msg.encode( "ascii" ) )
    mline = recvmultiline( ssl_sock )
    for l in mline:
        print( l )

    # The AUTHORIZATION State
    CUENTA = input( "Introduce tu cuenta asociada al correo del servidor local: " )
    msg = "{} {}\r\n".format( ComPOP3.User, CUENTA )
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isPOPerror( msg ):
        exit( 1 )

    CLAVE = getpass.getpass()
    msg = "{} {}\r\n".format( ComPOP3.Pass, CLAVE )
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    print( msg )
    if isPOPerror( msg ):
        exit( 1 )
    else:
        print( "Usuario autenticado en servidor POP3." )

    # The TRANSACTION State
    msg = "{}\r\n".format( ComPOP3.Stat )
    print( ComPOP3.Stat )
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isPOPerror( msg ):
        exit( 1 )
    tokens = msg.split()
    print( 'Número de mensajes: {}, Tamaño del buzón: {}'.format( tokens[1], int2bytes( int(tokens[2]) ) ))
    num_msgs = int( tokens[1] )

    # Lista de asignaturas
    lasign = ['SAR', 'SZA']
    # Lista de contadores
    lcont = dict()
    for asign in lasign:
        lcont[ asign ] = 0
    for i in range( num_msgs ):
        msg = "{} {} 0\r\n".format( ComPOP3.Top, i + 1 )
        ssl_sock.sendall( msg.encode( "ascii" ) )
        mline = recvmultiline( ssl_sock )
        for l in mline:
            if "Subject:" in l:
                for asign in lasign:
#                    if asign + ':' in l:
                    if asign in l:
                        lcont[ asign ] += 1
                        break
                break
    for asig, cont in lcont.items():
        print( "{}: {}".format( asig, cont ) )

    # The UPDATE State
    # Cerrar sesión de usuario POP3
    msg = "{}\r\n".format( ComPOP3.Quit )
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isPOPerror( msg ):
        exit( 1 )
    else:
        print( msg )
    # Cerrar conexión segura con servidor POP3
    ssl_sock.close()

    #####################################
    # Enviar mensaje SMTP servidor seguro
    #####################################
    serv_smtp = (SERV_SMTP, PORT_SMTP)

    s = socket.socket( socket.AF_INET, socket.SOCK_STREAM )
    s.connect( serv_smtp )


    # Saludo del servidor SMTP
    msg = recvline( s ).decode( "ascii" )
    if isSMTPerror( msg ):
        exit( 1 )

    # Client Initiation
    msg = "{} {}\r\n".format( ComSMTP.Ehlo, 'cliente SAR' )
    s.sendall( msg.encode( "ascii" ) )
    mline = recvEHLOmultiline( s )
    for l in mline:
        print( l )

    """A COMPLETAR POR EL/LA ESTUDIANTE:
    Conexión segura con el servidor SMTP
    """
    msg = "{}\r\n".format(ComSMTP.STLS)
    s.sendall( msg.encode( "ascii" ) )
    mline = recvline( s ).decode("ascii")
    if isSMTPerror(mline, ComSMTP.STLS):
        print("error aquiiiiii")
        exit(1)
    else:
        print(msg)

    ssl_sock = context.wrap_socket( s, server_hostname = SERV_SMTP)
    # ssl_sock.connect( serv_smtp )

    """A COMPLETAR POR EL/LA ESTUDIANTE:
    Certificado del servidor SMTP
    """
    cert=ssl_sock.getpeercert()
    ssl.match_hostname(cert, SERV_SMTP)

    """AVISO: A REALIZAR POR EL/LA ESTUDIANTE:
    Asegurate que todas las comunicaciones que se hacían con el socket
    ahora se hagan con un socket seguro
    """

    """A COMPLETAR POR EL/LA ESTUDIANTE:
    Client Initiation with TLS (Recomendado)
    """

    # Mail Transaction
    ## FROM
    dir_origen = 'jechevarria031@ikasle.ehu.eus'
    msg = "{} <{}>\r\n".format( ComSMTP.From, dir_origen )
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isSMTPerror( msg, ComSMTP.From ):
        exit( 1 )

    ## TO
    dir_destino = 'jechevarria031@ikasle.ehu.eus'
    msg = "{} <{}>\r\n".format( ComSMTP.To, dir_destino )
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isSMTPerror( msg, ComSMTP.To ):
        exit( 1 )

    ## DATA
    msg = "{}\r\n".format( ComSMTP.Data )
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isSMTPerror( msg, ComSMTP.Data ):
        exit( 1 )

    ## Cuerpo mensaje
    lineas = []
    ## Header section
    ## Origination Date Field
    lineas.append( "{} {}".format( FieldIMF.Date, time.ctime() ))
    ## Originator Fields
    lineas.append( "{} Asignatura SAR de UPV-EHU <{}>".format( FieldIMF.From, dir_origen )  )
    ## Destination Address Fields
    lineas.append( "{} Estudiante {} <{}>".format( FieldIMF.To, CUENTA, dir_destino )  )
    ## Informational Fields
    lineas.append( "{} {}".format( FieldIMF.Subject, "SAR: Resultado consulta servidor POP" )  )
    # empty line
    lineas.append( "" )
    ## Body
    lineas.append( "Número mensajes por aula virtual de eGela" )
    lineas.append( "-----------------------------------------" )
    for asig, cont in lcont.items():
        lineas.append( "{}: {}".format( asig, cont ) )
    lineas.append( "Agur\r\nAsignatura SAR" )

    for l in lineas:
        ssl_sock.sendall( "{}\r\n".format( l ).encode() )

    ## Fin cuerpo mensaje
    msg = ".\r\n"
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isSMTPerror( msg, ComSMTP.Helo ):
        exit( 1 )
    else:
        print( msg )

    # Cerrar sesión de usuario SMTP
    msg = "{}\r\n".format( ComSMTP.Quit)
    ssl_sock.sendall( msg.encode( "ascii" ) )
    msg = recvline( ssl_sock ).decode( "ascii" )
    if isSMTPerror( msg, ComSMTP.Quit ):
        exit( 1 )
    else:
        print( msg )
    # Cerrar conexión segura con servidor SMTP
    s.close()
