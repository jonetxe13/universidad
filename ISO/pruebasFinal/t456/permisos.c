#include <fcntl.h>
#include <pwd.h>
// #include <stdio.h>
#include <stdio.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <string.h>

int main(int argc, char *argv[]){
  int fd;
  struct stat nombre;
  int nose;
  // printf("error abriendo el arhivo");

  if(argc < 2){
    printf("Uso: %s archivo1 .. archivoN", argv[0]);
    _exit(-1);
  }
  if((nose = stat(argv[1], &nombre))==-1){
    printf("error cogiendo el archivo");
    _exit(-1);
  }
  printf("antes permisos: %u\n",nombre.st_mode);
  mode_t new_mode = nombre.st_mode & S_IXUSR;
  if(chmod(argv[1], S_IROTH) == -1){
    printf("error cambiando los permisos");
  }
  nose = stat(argv[1], &nombre);
  printf("despues permisos: %u\n",nombre.st_mode);
  
  return 0;
}
