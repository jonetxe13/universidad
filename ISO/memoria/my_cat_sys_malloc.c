#include <stdio.h>
#include <stdlib.h>
#include <sys/types.h>
#include <sys/stat.h>
#include <fcntl.h>

main(int argc, char *argv[])
{
   int i;
   char *buffer;
   int f;
   size_t n, buffer_size;

   if (argc != 3) {
      fprintf(stderr, "usage: %s BUFFER_SIZE FILE\n", argv[0]);
      exit(1);
   }

   buffer_size = atoi(argv[1]);

   if ((buffer = malloc(buffer_size)) == NULL) {
      perror("malloc");
      exit(1);
   }

   if ((f = open(argv[2], O_RDONLY)) == -1) {
      perror("open");
      exit(1);
   }

   i=0;
   while ((n = read(f, buffer, buffer_size)) > 0) {
      i++;
      write(1, buffer, n);
   }
   i++;
   printf("Number of calls to read: %d\n", i);

   close(f);

   free(buffer);

   exit(0);
}
