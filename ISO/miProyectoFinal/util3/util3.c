#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]){
  if(argc != 4){
    printf("Uso: %s programa1 programa2", argv[0]);
  }
  // printf("hola que tal\n");
  if(argv[1][0] == 'C'){
    // printf("se mete en el primer if\n");
    if(fork() == 0){
      char *pr[] = { argv[2], NULL };
      execvp(argv[2], pr);
      printf("error en execlp1\n");
      _exit(-1);
    }
    if(fork() == 0){
      char *pr2[] = { argv[3], NULL };
      execvp(argv[3], pr2);
      printf("error en execlp2\n");
      _exit(-1);
    }
    wait(NULL);
  }
  if( argv[1][0] == 'S'){
    for(int i = 0; i < 2; i++){
    // printf("se mete en el for\n");
      if(fork() == 0){
        char *pr3[] = { argv[i+2], NULL };
        execvp(argv[i+2], pr3);
        printf("error en execlp2\n");
        _exit(-1);
      }
      wait(NULL);
    }
  }
  return 1;
}
