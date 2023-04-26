/* parent.c */

#include <sys/types.h>
#include <unistd.h>
#include <signal.h>
#include <stdio.h>
#include <stdlib.h>
#include <time.h>

main(int argc, char *argv[])	
{
   int childid, clockid, id, aux;
   time_t t;

   printf("--parent-process: %d\n", getpid());
   if ((clockid = fork()) == 0) execlp("clock", "clock", argv[1], NULL);
   printf("--clock-process: %d\n", clockid);
   if ((childid = fork()) == 0) execvp(argv[2], &(argv[2]));
   printf("--child-process: %d\n", childid);
   t = time(NULL);
   id = wait(NULL);
   if (id == childid) {   /* child finishes first; stop clock */
      kill(clockid, SIGKILL);
      wait(NULL);
      aux = 0;
   }
   else {   /* clock finishes first; force child to finish */
      kill(childid, SIGKILL);
      wait(NULL);
      printf("--overtime\n");
      aux = -1;
  }
  t = time(NULL) - t;
  printf("--child time: %d\n", t);
  exit(aux);
}
