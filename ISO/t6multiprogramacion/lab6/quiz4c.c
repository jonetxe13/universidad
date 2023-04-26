#include <unistd.h>

main()
{
   if (fork() == 0) execlp("sleep", "sleep", "10", NULL);
   wait(NULL);
   if (fork() == 0) execlp("sleep", "sleep", "20", NULL);
   wait(NULL);
   if (fork() == 0) execlp("sleep", "sleep", "30", NULL);
   wait(NULL);
   //while (wait(NULL) != -1) ;
}
