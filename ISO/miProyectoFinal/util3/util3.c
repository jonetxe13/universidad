#include <stdio.h>
#include <time.h>
#include <unistd.h>
#include <sys/wait.h>

int main(int argc, char *argv[]){
  struct timespec start, end;
  long seconds, nanoseconds;
  double mtime;
  clock_gettime(CLOCK_REALTIME, &start);
  if(argc <= 3){
    printf("Uso: %s programa1 programa2", argv[0]);
    return -1;
  }
  if(argv[1][0] == 'C'){
    int i = 0;
    for(i; i < argc-1; i++){
      if(fork() == 0){
//        printf("%s\n", argv[i+2]);
        char *pr[] = { argv[i+2], NULL };
        execvp(argv[i+2], pr);
        printf("error en execlp, el nombre de la utilidad no se ha encontrado\n");
    	return -1;
      }
    }
    wait(NULL);
  }
  if(argv[1][0] == 'S'){
	int i = 0;
    for(i; i < argc-1; i++){
      if(fork() == 0){
        char *pr3[] = { argv[i+2], NULL };
        execvp(argv[i+2], pr3);
        printf("error en execlp, el nombre de la utilidad no se ha encontrado\n");
    	return -1;
      }
      wait(NULL);
    }
  }
  wait(NULL);
  clock_gettime(CLOCK_REALTIME, &end);
  seconds  = end.tv_sec  - start.tv_sec;
  nanoseconds = end.tv_nsec - start.tv_nsec;
  mtime = (seconds * 1000) + (nanoseconds / 1000000.0);

  printf("Tiempo de ejecución: %.0f milisegundos\n", mtime);
  return 1;
}
