#include <unistd.h>
#include <stdio.h>
#include <sys/stat.h>
#include <dirent.h>
#include <fcntl.h>
#include <string.h>

int main(int argc, char *argv[])
{
  DIR *dir;
  struct dirent *rdir;
  if(argc != 2){
    printf("Uso: %s directorio", argv[0]);
    return -1;
  }
  if((dir=opendir(argv[1])) == NULL){
    printf("error abriendo el directorio");
    return -1;
  }

  int f;
  int st;
  struct stat fst;
  mode_t new_mode;
  char filepath[128];
  while ((rdir=readdir(dir)) != NULL) {
    strcpy(filepath, argv[1]);
    strcat(filepath, "/");
    strcat(filepath, rdir->d_name);
//    f=open(rdir->d_name, O_RDONLY);
    st=stat(rdir->d_name, &fst);
    printf("nombre: %s\n", filepath);
    if(strcmp(rdir->d_name, ".") != 0 && strcmp(rdir->d_name, "..") != 0){
        if (rdir->d_type == DT_REG) {
        printf("nombre: %s  mode: %u\n", rdir->d_name , fst.st_mode);
        //eliminar los permisos de grupo y others
        new_mode = fst.st_mode & ~0177;
        printf("nuevo mode: %u\n", new_mode);
      }
      else if (rdir->d_type == DT_DIR) {
          printf("nombre: %s  mode: %u\n", rdir->d_name, fst.st_mode);
          //añadir el permiso de ejecución para el propietario y grupo dejando el resto como estaban
          new_mode = (fst.st_mode | 0110) & ~0003;
      }
      else{
          printf("nombre: %s  mode: %u\n", rdir->d_name, fst.st_mode);
          //eliminar todos los permisos al grupo del elemento y al resto de los usuarios dejando el resto como estaban
          new_mode = fst.st_mode & ~077;
      }
      if (chmod(filepath, new_mode) == -1) {
          write(1, "Error changing permissions\n",strlen("Error changing permissions\n"));
      }
    }
  }
  return 1;
}