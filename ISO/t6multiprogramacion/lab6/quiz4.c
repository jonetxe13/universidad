#include <unistd.h>

main()
{
   execlp("sleep", "sleep", "10", NULL);
   execlp("sleep", "sleep", "20", NULL);
   execlp("sleep", "sleep", "30", NULL);
   while (wait(NULL) != -1) ;
}
