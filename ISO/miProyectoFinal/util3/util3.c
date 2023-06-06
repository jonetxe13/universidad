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
<<<<<<< HEAD
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
=======
  }
  if(argv[1][0] == 'C'){
    // printf("se mete en el primer if\n");
    int i = 0;
    for(i; i < argc-1; i++){
      if(fork() == 0){
        printf("%s\n", argv[i+2]);
        char *pr[] = { argv[i+2], NULL };
        execvp(argv[i+2], pr);
        printf("error en execlp1\n");
        _exit(-1);
>>>>>>> e06de3fb815f0b5959863d2783fee4e128b2e675
      }
    }
    wait(NULL);
  }
  if(argv[1][0] == 'S'){
	int i = 0;
    for(i; i < argc-1; i++){
<<<<<<< HEAD
      if(fork() == 0){
        char *pr3[] = { argv[i+2], NULL };
        execvp(argv[i+2], pr3);
        printf("error en execlp, el nombre de la utilidad no se ha encontrado\n");
    	return -1;
=======
    // printf("se mete en el for\n");
      if(fork() == 0){
        printf("%s\n", argv[i+2]);
        char *pr3[] = { argv[i+2], NULL };
        execvp(argv[i+2], pr3);
        printf("error en execlp2\n");
        _exit(-1);
>>>>>>> e06de3fb815f0b5959863d2783fee4e128b2e675
      }
      wait(NULL);
    }
  }
<<<<<<< HEAD
  wait(NULL);
=======
>>>>>>> e06de3fb815f0b5959863d2783fee4e128b2e675
  clock_gettime(CLOCK_REALTIME, &end);
  seconds  = end.tv_sec  - start.tv_sec;
  nanoseconds = end.tv_nsec - start.tv_nsec;
  mtime = (seconds * 1000) + (nanoseconds / 1000000.0);

  printf("Tiempo de ejecución: %.0f milisegundos\n", mtime);
  return 1;
}
