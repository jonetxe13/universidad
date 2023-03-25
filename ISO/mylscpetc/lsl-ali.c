
// mi_lsl.c
// Similar a ls -ali
#include <stdio.h> 
#include <stdlib.h> 
#include <dirent.h> 
#include <string.h> 
#include <sys/types.h> 
#include <sys/stat.h>
#include <unistd.h>

int main (int argc, const char *argv[]) {
  DIR *dir; 
  struct dirent *rdir;
  struct stat st;
  char *error1 = "No hay parametros suficientes \n Uso: %s directorio";
  char *error2 = "No existe el directorio.\n";
  char *error3 = "No se ha podido obtener el inode:";
  char path[256];

  if (argc!=2){
    fprintf(stderr, "%s %s", error1, argv[0]); exit(1);
  }
  if ((dir=opendir(argv[1]))==NULL){
    fprintf(stderr, "%s", error2); exit(2);
  }

  printf("Mode NLink Size TCTime Inode Nombre \n"); 

  while ((rdir=readdir(dir))!=NULL){
    sprintf(path, "%s/%s", argv[1], rdir->d_name);
    if (stat(path, &st)==0){
      printf("%06o %3d %8d %10d %8ld %s\n",
             st.st_mode, st.st_nlink, st.st_size, st.st_ctime, rdir->d_ino, path);
    } else {
      fprintf(stderr, "%s", error3); fprintf(stderr, "%s\n", path);
      exit(3);
    }
  }
  closedir(dir);
  exit(0); // Todo OK
}
