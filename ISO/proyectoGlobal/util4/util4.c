// #include <stdlib.h>
#include <string.h>
#include <unistd.h>
#include <sys/wait.h>
#define EXIT_FAILURE 1

int main(int argc, char *argv[]) {
  int pipefd[2];
  pid_t cpid;
  char buf;
  int *counts = calloc(argc - 1, sizeof(int));
  char line[1024];
  int line_len = 0;
  char *args[] = {"last", NULL};

  if (pipe(pipefd) == -1) {
    write(2, "pipe error", strlen("pipe error"));
    exit(EXIT_FAILURE);
  }

  cpid = fork();
  if (cpid == -1) {
    write(2, "fork", 4);
    exit(EXIT_FAILURE);
  }

  if (cpid == 0) { // Proceso hijo
    close(pipefd[0]); // Cierra el extremo de lectura del pipe
    dup2(pipefd[1], STDOUT_FILENO); // Redirige la salida estándar al pipe
    close(pipefd[1]); // Cierra el extremo de escritura del pipe
    execvp(args[0], args); // Ejecuta el comando last
    write(2, "execvp", 6);
    exit(EXIT_FAILURE);
  } else { // Proceso padre
    close(pipefd[1]); // Cierra el extremo de escritura del pipe
    write(STDOUT_FILENO, argv[1], strlen(argv[1]));
    while (read(pipefd[0], &buf, 1) > 0) {
      if (buf == '\n') {
        line[line_len] = '\0';
          for (int i = 1; i < argc; i++) {
            if (strncmp(line, argv[i], strlen(argv[i])) == 0) {
              counts[i - 1]++;
                break;
            }
          }
        line_len = 0;
      } else {
        line[line_len++] = buf;
      }
    }
    close(pipefd[0]); // Cierra el extremo de lectura del pipe
    wait(NULL); // Espera a que el proceso hijo termine

    for (int i = 1; i < argc; i++) {
      write(STDOUT_FILENO, argv[i], strlen(argv[i]));

      // printf("%s: %d\n", argv[i], counts[i - 1]);
    }
        // write(STDOUT_FILENO, ":\n", strlen(":\n"));
        // while (read(pipefd[0], &buf, 1) > 0) {
        //     // Procesa la información en buf
        //     write(STDOUT_FILENO, &buf, 1);
        // }
        // close(pipefd[0]); // Cierra el extremo de lectura del pipe
        // wait(NULL); // Espera a que el proceso hijo termine
  }
   return 0;
}
