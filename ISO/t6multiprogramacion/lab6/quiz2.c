#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>

main(int argc, char *argv[])
{
   int i, n, id;

   if (argc == 1) {
      printf("Usage: %s <number>\n", argv[0]);
      exit(1);
   }

   printf("%d\n", getpid());

   for (i=0; i<atoi(argv[1]); i++)
      if (fork() == 0) execlp("who", "who", NULL);

   n=0;
   while ((id = wait(NULL)) != -1) {
      printf("%d\n", id);
      n++;
   }
   printf("%d\n", n);
}
