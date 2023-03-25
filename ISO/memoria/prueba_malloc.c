#include <stdlib.h>
#include <stdio.h>

int main()
{
   char *buffer;
   unsigned long n=1;
   int i=0;

   while (1) {
      if ((buffer=malloc(n)) != NULL) {
         free(buffer);
         printf("malloc(2^%2d = %11ld bytes) OK (%p)\n", i, n, buffer);
         i++;
         n=2*n;
      }
      else {
         printf("malloc(2^%2d = %11ld bytes) FAILED (%p)\n", i, n, buffer);
         perror("malloc");
         break;
      }
   }
}
