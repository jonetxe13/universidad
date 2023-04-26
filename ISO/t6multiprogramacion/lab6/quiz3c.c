#include <stdio.h>
#include <sys/types.h>
#include <unistd.h>

main()
{
    printf("AAAA\n");
    fork();
    printf("BBBB\n");
    if (fork() == 0) execlp("echo", "echo", "CCCC", NULL);
    printf("DDDD\n");
}

