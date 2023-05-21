#include <pwd.h>
#include <sys/types.h>
#include <grp.h>

int main(int argc, char *argv[]){

  if(argc < 2){
    printf("%d", argc);
    printf("Uso: %s archivo1 .. archivoN", argv[0]);
  }
  write(1, "hola que tal", strlen("hola que tal"));
  return 0;
}
