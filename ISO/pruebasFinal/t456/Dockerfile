FROM gcc:4.9
COPY ./* /usr/src/.
WORKDIR /usr/src
RUN ls
RUN gcc -o permisos permisos.c
CMD ["./permisos prueba.txt"]
