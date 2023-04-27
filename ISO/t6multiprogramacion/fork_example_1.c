#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>

int main()   // Try this example without and with the final sleep(1) instruction. Compare both cases
{
   fork();
   fork();
   fork();
   printf("%d %d Hola\n", getpid(), getppid());
   // wait(NULL);
   // sleep(1);
}
