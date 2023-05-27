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
    printf("Uso: %s directorio");
  }
  if((dir=opendir(argv[1])) == NULL){
    printf("error abriendo el directorio");
    _exit(-1);
  }

  int f;
  int st;
  struct stat fst;
  mode_t new_mode;
  char filepath[128];
  while ((rdir=readdir(dir)) != NULL) {
    f=open(rdir->d_name, O_RDONLY);
    st=stat(rdir->d_name, &fst);
    strcpy(filepath, argv[1]);
    strcat(filepath, "/");
    strcat(filepath, rdir->d_name);
    if(strcmp(rdir->d_name, ".") != 0 && strcmp(rdir->d_name, "..") != 0){
      if (rdir->d_type == DT_REG) {
        printf("nombre: %s  mode: %u\n", rdir->d_name , fst.st_mode);
        //eliminar los permisos de grupo
        new_mode = 0100600;
        printf("nuevo mode: %u\n", new_mode);
      }
      else if (rdir->d_type == DT_DIR) {
        printf("nombre: %s  mode: %u\n", rdir->d_name, fst.st_mode);
        new_mode = 0040770;
      }
      else{
        printf("nombre: %s  mode: %u\n", rdir->d_name, fst.st_mode);
        new_mode = 0040600;
      }
      if (chmod(filepath, new_mode) == -1) {
        write(1, "Error changing permissions\n",strlen("Error changing permissions\n"));
      }
      printf("ha funcionado?");
      close(f);
      return 0;
    }
  }
  return 1;
}
