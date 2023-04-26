#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <signal.h>

main()   /* example2d */
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
   else {   /* parent */
      printf("I am the %d parent, my child is %d (I will force my child to finish after 4 seconds...)\n",
             getpid(), pid);
      sleep(4);
      kill(pid, SIGKILL);
      wait(NULL);
   }

   printf("I am exiting %d\n", getpid());   /* both */
   exit(0);
}
