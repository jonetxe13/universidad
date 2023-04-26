#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

#define error(a) {perror(a); exit(1);};

main()   /* example3 */
{
   int pid;

   pid = fork();
   switch (pid){
      case -1:   /* error */
         exit(-1);
      case 0:   /* child */
         execlp("ps", "ps", "-f", NULL);
         error("execlp");
         break;
      default:   /* parent */
         printf("%d parent, child is %d\n", getpid(), pid);
         wait(NULL);
   }
   exit(0);
}
