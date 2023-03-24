//esto es con llamadas al sistema
#include <unistd.h>
#define TAMANO_BUFER 512

int main(int argc, char *argv[]){
  int n;
  char buf[TAMANO_BUFER];
  while((n=read(0, buf, TAMANO_BUFER)) > 0)
    write(1, buf, n);
  return 0;
}
