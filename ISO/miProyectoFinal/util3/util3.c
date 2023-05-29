#include <stdio.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]){
  clock_t start = clock();
  if(argc <= 3){
    printf("Uso: %s programa1 programa2", argv[0]);
  }
  if(argv[1][0] == 'C'){
    // printf("se mete en el primer if\n");
    for(int i = 0; i < argc-1; i++){
      if(fork() == 0){
        printf("%s\n", argv[i+2]);
        char *pr[] = { argv[i+2], NULL };
        execvp(argv[i+2], pr);
        printf("error en execlp1\n");
        _exit(-1);
      }
    }
    wait(NULL);
  }
  if(argv[1][0] == 'S'){
    for(int i = 0; i < argc-1; i++){
    // printf("se mete en el for\n");
      if(fork() == 0){
        printf("%s\n", argv[i+2]);
        char *pr3[] = { argv[i+2], NULL };
        execvp(argv[i+2], pr3);
        printf("error en execlp2\n");
        _exit(-1);
      }
      wait(NULL);
    }
  }
  clock_t end = clock();
  float tiempo = (float)(end - start) / 1000;
  printf("(el tiempo total es: %f)", tiempo);
  return 1;
}
