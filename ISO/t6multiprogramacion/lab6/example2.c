#include <stdio.h>
#include <stdlib.h>

main()   /* example2 */
{
   int pid;

   pid = fork();

   if (pid == 0)   /* child */
      printf("I am the %d child, my parent is %d\n",
             getpid(), getppid());
   else   /* parent */
      printf("I am the %d parent, my child is %d\n",
             getpid(), pid);

   printf("I am exiting %d\n", getpid());   /* both */
   exit(0);
}
