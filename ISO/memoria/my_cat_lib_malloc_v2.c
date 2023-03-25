#include <stdio.h>
#include <stdlib.h>

main(int argc, char *argv[])
{
   int i, continuar = 1;
   char *buffer;
   FILE *f;
   size_t n, buffer_size;

   if (argc != 3) {
      fprintf(stderr, "usage: %s BUFFER_SIZE FILE\n", argv[0]);
      exit(1);
   }

   buffer_size = atoi(argv[1]);

   if ((f = fopen(argv[2], "r")) == NULL) {
      perror("fopen");
      exit(1);
   }

   i=0;
   while (continuar == 1) {
      if ((buffer = malloc(buffer_size)) == NULL) {
         perror("malloc");
         exit(1);
      }
      if ((n = fread(buffer, sizeof(char), buffer_size, f)) > 0) {
         i++;
         fwrite(buffer, sizeof(char), n, stdout);
         free(buffer);
      }
      else
         continuar = 0;
   }
   i++;
   printf("Number of calls to fread: %d\n", i);

   fclose(f);

   free(buffer);

   exit(0);
}
