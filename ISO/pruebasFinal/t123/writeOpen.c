#include <stdio.h>
#include <sys/fcntl.h>
#include <unistd.h>
#include <string.h>
#include <dirent.h>

int main(int argc, char *argv[])
{
  DIR *dir;
  struct dirent *rdir;
  if(argc < 2){
    write(2, "Uso: writeOpen <texto>", strlen("Uso: writeOpen <texto>"));
    return -1;
  }
  if((dir=opendir(argv[1])) == NULL){
    write(2, "error abriendo el directorio", strlen("error abriendo el directorio"));
    return -2;
  }
  char *buf;
  char *linea;
  int error;
  struct stat st;
  while((rdir=readdir(dir)) != NULL){
    write(1, rdir->d_name, strlen(rdir->d_name));
    write(1, "\n", 1);
    char *path="/home/jonetxe13/Desktop/universidad/ISO/pruebasFinal/t123/pruebadir/";
    char *pathCompleto=strcat(path,rdir->d_name);
    //imprimir el contenido de un archivo en pantalla
    error = stat(pathCompleto, st);
    if(error != -1){
      error = read(st.st_gid, buf, 20);
      printf("el buffer es: %s\n", buf);
      write(1, buf, strlen(buf));
    }
  }
  closedir(dir);

  return 0;
}
