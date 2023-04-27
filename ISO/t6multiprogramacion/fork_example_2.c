#include <sys/types.h>
#include <unistd.h>
#include <stdio.h>

int main()
{
   printf("%d %d A\n", getpid(), getppid());
   fork();
   printf("%d %d B\n", getpid(), getppid());
   fork();
   printf("%d %d C\n", getpid(), getppid());
   fork();
   printf("%d %d D\n", getpid(), getppid());
   sleep(1);
}
