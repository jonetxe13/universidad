#include <stdio.h>
#include <unistd.h>
#include <dirent.h>
#include <string.h>
#include <fcntl.h>

#define BUFSIZE 512

#define MAX_NAME 512
struct s_Registro
{
   char PathName[MAX_NAME];
   unsigned int Posicion;
   unsigned int Indice;
};

int main(int argc, char *argv[]){ 
  DIR *dir;
  struct dirent *rdir;
  int fd;
  int f;
  if(argc != 5){
    printf("Uso: %s dir resultado.dat tabla.dat index", argv[0]);
    return -1;
  }
  if((dir=opendir(argv[1])) == NULL){
    printf("error abriendo el directorio");
    return -1;
  }
  char buf[BUFSIZE];
  char filepath[MAX_NAME];
  int result=open(argv[2], O_CREAT | O_WRONLY, 0644);
  unsigned int posicion = lseek(result, 0, SEEK_CUR);
  printf("tabla: %s", argv[3]);
  int tabla=open(argv[3], O_CREAT | O_WRONLY, 0644);
  unsigned int posicion2 = lseek(tabla, 0, SEEK_CUR);
  
  while((rdir=readdir(dir)) != NULL) {
    if(rdir->d_type == DT_REG){
      strcpy(filepath, argv[1]);
      strcat(filepath, "/");
      strcat(filepath, rdir->d_name);
      printf("filepath %s\n", filepath);
      fd=open(filepath, O_RDONLY);
      if(fd == -1){
        printf("error leyendo el archivo\n");
      }

      while((f=read(fd, buf, BUFSIZE)) > 0) {
        write(result, buf, f);
        int padding=BUFSIZE-(f%BUFSIZE);
        if(padding< BUFSIZE){
          printf("padding es: %d\n", padding);
          memset(buf, 0, padding);
          write(result, buf, padding);
        }
      }
      
      struct s_Registro prueba;
      strcpy(prueba.PathName, filepath);
      prueba.Indice=atoi(argv[4]);
      prueba.Posicion=posicion;
      write(tabla, &prueba, sizeof(prueba));
    }
  }
  
  return 0;
}
