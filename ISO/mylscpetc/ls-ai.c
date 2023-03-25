
// mi_ls.c
// Similar a ls -ai

#include <unistd.h> 
#include <errno.h>
#include <string.h> 
#include <dirent.h>
#include <stdio.h>

int main(int argc, const char *argv[]) {
  DIR *dir;
  struct dirent *rdir;
  char *error1 = "No hay parametros suficientes. Si quiere el actual ponga un punto\n";
  char *error2 = "No existe el directorio.\n";
  char *error3 = "No se ha podido leer.\n";
  char linea[80];

  if (argc!=1) {
    if (argc != 2) {
      write(2, error1, strlen(error1)); 
      return 2;
    }
    if ((dir=opendir(argv[1]))==NULL){ 
        write(2, error2, strlen(error2)); 
        return 3;
    }
  } else {
    if ((dir=opendir("."))==NULL) { 
      write(2, error2, strlen(error2)); 
      return 3;
    }
  }

  while ((rdir=readdir(dir))!=NULL) {
    sprintf(linea, "%d %s\n", rdir->d_ino, rdir->d_name); 
    write(1, linea, strlen(linea));
  }
  closedir(dir);
  return 0;
}
