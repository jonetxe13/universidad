#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>

#define error(a) {perror(a); exit(1);};

main(int argc, char *argv[])   /* example4 */
{
   int pid;

   if (argc == 1) error("parameter(s), please");

   pid = fork();
   switch (pid){
      case -1:   /* error */
         exit(-1);
      case 0:   /* child */
         execvp(argv[1], &(argv[1]));
         error("execvp");
         break;
      default:   /* parent */
         printf("%d parent, child is %d\n", getpid(), pid);
         wait(NULL);
   }
   exit(0);
}
