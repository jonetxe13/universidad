#include <unistd.h>

main()
{
   if (fork() == 0) execlp("sleep", "sleep", "10", NULL);
   if (fork() == 0) execlp("sleep", "sleep", "20", NULL);
   if (fork() == 0) execlp("sleep", "sleep", "30", NULL);
   // while (wait(NULL) != -1) ;
}
