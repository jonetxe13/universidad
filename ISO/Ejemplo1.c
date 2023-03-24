/**
  * @file Ejemplo1.c
  * @author G.A.
  * @date 21 Jan 2020
  * @brief C File example to print repeated messages to stdout
  *
  */

  #include <stdio.h>         // Pre-procesor directive (include stdio.h header file)
  #include <stdlib.h>        // Pre-procesor directive (include stdlib.h header file)

  int main (int argc, const char * argv[])              // Main function (program entry point).
  {
    int i, j;

    if (argc < 3) {
        printf("Uso: %s veces mensaje\n", argv[0]);
        exit(1);                                     // exit function: terminate proccess and the value (1 & 0377) is
                                                     // returned to the parent process
    }
    if (atoi(argv[1]) < 0){                           // atoi(arg[1]) convert a string (program argument 1) to an integer
    	for (j=2; j<argc; j++)
    		printf("%s\n", argv[j]);         // Formatted output conversion. Write to stdout, the standard output stream
    }
             exit(1);
    for (i=1; i<=atoi(argv[1]); i++){
	    sleep(1);
    	printf("%d\n", i);
    }
    for (j=2; j<argc; j++){
    	printf("%s\n", argv[j]);         // Formatted output conversion. Write to stdout, the standard output stream
    }
    exit(0);
  }
