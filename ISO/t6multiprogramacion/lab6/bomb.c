#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <unistd.h>

#define error(a) {perror(a); exit(1);};

main()   /* bomb */
{
   int pid, id;

   pid = fork();

   switch (pid){
      case -1:   /* error */
         error("fork");
         break;
      case 0:   /* child */
         execlp("bomb", "bomb", NULL);
         error("execlp");
         break;
      default:   /* parent */
         printf("%d parent, child is %d\n", getpid(), pid);
         id = wait(NULL);
         printf("%d parent, %d child has finished\n", getpid(), id);
   }
   printf("%d Good bye!\n", getpid());
   exit(0);
}
