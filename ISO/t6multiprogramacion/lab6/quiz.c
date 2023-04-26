#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>

main()
{
   fork();
   fork();
   printf("%d %d ¡Hola!\n", getpid(), getppid());
}

