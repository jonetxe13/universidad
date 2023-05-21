#include <fcntl.h>
#include <stdio.h>
#include <sys/fcntl.h>
#include <unistd.h>
#include <string.h>
#include <dirent.h>

int main(int argc, char *argv[]){
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
  char buf[1024];
  int fd;
  int f;
  char path[1024];
  while((rdir=readdir(dir)) != NULL){
    if (rdir->d_type == DT_REG && strcmp(rdir->d_name, ".") != 0 && strcmp(rdir->d_name, "..") != 0) {
      strcpy(path, argv[1]);
      strcat(path, "/");
      strcat(path, rdir->d_name);
      write(1, path, strlen(path));
      write(1, ":\n", 2);
      fd = open(path, O_RDONLY);
      if(fd == -1){
        perror("error abriendo el archivo");
        continue;
      }
      while((f = read(fd, buf, sizeof(buf))) > 0){
        write(1, buf, f);
      }
      close(fd);
    }
  }
  closedir(dir);
  return 0;
}
