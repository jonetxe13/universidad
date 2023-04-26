#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

#define error(a) {perror(a); exit(1);};
#define BUFSIZE 512

main()
{
   int n;
   char buf[BUFSIZE];

   write(1, "my_bash_v1_system> ", strlen("my_bash_v1_system> "));
   while ((n = read(0, buf, BUFSIZE)) > 0) {
      buf[n-1] = '\0';
      system(buf);
      write(1, "my_bash_v1_system> ", strlen("my_bash_v1_system> "));
   }
   if (n == -1) error("read");
   write(1, "\n", 1);
}
