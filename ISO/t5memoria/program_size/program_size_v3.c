#include <stdio.h>
#include <string.h>

char buffer[1048576] = "Hello";

main()
{
   printf("buffer before strcpy: %s\n", buffer);
   printf("sizeof(buffer) before strcpy: %d\n", sizeof(buffer));
   printf("strlen(buffer) before strcpy: %d\n", strlen(buffer));

   strcpy(buffer, "Bye");

   printf("buffer after strcpy: %s\n", buffer);
   printf("sizeof(buffer) after strcpy: %d\n", sizeof(buffer));
   printf("strlen(buffer) after strcpy: %d\n", strlen(buffer));
}
