/* my_sh1.c */

#include <stdio.h>
#include <stdlib.h>
#include <errno.h>
#include <string.h>

#define error(a) {perror(a); exit(1);};
#define BUFSIZE 512
#define MAXARG 10

void get_parameters(char *buf, int n, char *argk[], int ma);

main(int argc, char *argv[])
{
   int n, pid;
   char buf[BUFSIZE];
   char *arg[MAXARG];

   for (n = 0; n < BUFSIZE; n++) buf[n] = '\0';

   /* read */
   write(1, "my_sh1> ", strlen("my_sh1> "));
   while ((n = read(0, buf, BUFSIZE)) > 0)
   {
      buf[n] = '\n';
      n++;
      get_parameters(buf, n, arg, MAXARG);

      if (arg[0] == NULL)
      {
         write(1, "my_sh1> ", strlen("my_sh1> "));
         continue;
      }

      switch (pid = fork())
      {
         case -1: error("fork");
                  break;
         case  0: /* child */
                  execvp(arg[0], arg);
                  error("exec");
                  break;
         default: /* parent */
	          printf("%d (%s ...) process created\n", pid, arg[0]);
                  /* wait until child finishes */
                  if (wait(NULL) != pid) error("wait");
                  for (n = 0; n < BUFSIZE; n++) buf[n] = '\0';
                  write(1, "my_sh1> ", strlen("my_sh1> "));
                  break;
      } 
   }
   printf("\n");
}

void get_parameters(char *buf, int n, char *argk[], int m)
{
   int i, j;

   for (i = 0, j = 0; (i < n) && (j < m); j++)
   {
      /* advance blanks */
      while (((buf[i] == ' ') || (buf[i] == '\n')) && (i < n)) i++;
      if (i == n) break;
      argk[j] = &buf[i];
      /* find blank */
      while ((buf[i] != ' ') && (buf[i] != '\n')) i++;
      buf[i++] = '\0';
   }
   argk[j] = NULL;
}
