#include <unistd.h>
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
  while ((rdir=readdir(dir)) != NULL) {
    f=open(rdir->d_name, O_RDONLY);
    st=stat(rdir->d_name, &fst);
    if (rdir->d_type == DT_REG) {
      printf("mode: %u\n", fst.st_mode);
      new_mode = fst.st_mode & S_IRWXU;
      printf("nuevo mode: %u\n", new_mode);
    }
    else if (rdir->d_type == DT_DIR) {
      printf("mode: %u\n", fst.st_mode);
      new_mode = fst.st_mode | S_IXUSR | S_IXGRP;
      printf("nuevo mode: %u\n", new_mode);
    }
  }
  return 1;
}
