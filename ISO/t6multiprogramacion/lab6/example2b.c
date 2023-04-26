#include <stdio.h>
#include <stdlib.h>

main()   /* example2b */
{
   int pid;

   pid = fork();

   if (pid == 0) {   /* child */
      printf("I am the %d child, my parent is %d (I am going to sleep for 10 seconds...)\n",
             getpid(), getppid());
      sleep(10);
      printf("I am the %d child, my parent is %d (I wake up)\n",
             getpid(), getppid());
   }
   else   /* parent */
      printf("I am the %d parent, my child is %d\n",
             getpid(), pid);

   printf("I am exiting %d\n", getpid());   /* both */
   exit(0);
}
