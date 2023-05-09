#include <stdio.h>
#include <unistd.h>
#include <sys/wait.h>
#include <time.h>
#define EXIT_FAILURE 1

int main(int argc, char *argv[]) {
    if (argc < 3) {
        char error[] = "Uso: lanzador_CS [C|S] comando1 comando2 ...\n";
        write(STDERR_FILENO, error, sizeof(error) - 1);
        _exit(EXIT_FAILURE);
    }

    char mode = argv[1][0];
    if (mode != 'C' && mode != 'S') {
        char error[] = "El primer argumento debe ser C o S\n";
        write(STDERR_FILENO, error, sizeof(error) - 1);
        _exit(EXIT_FAILURE);
    }

    time_t start = time(NULL);

    for (int i = 2; i < argc; i++) {
        pid_t pid = fork();
            // printf("el error pasa aqui: pid = %d\n", pid);
        if (pid == -1) {
            char error[] = "Error en fork\n";
            write(STDERR_FILENO, error, sizeof(error) - 1);
            _exit(EXIT_FAILURE);
        } else if (pid == 0) {
            // printf("el error pasa aqui: pid = %d\n", pid);
            char *args[] = {argv[i], NULL};
            execvp(args[0], args);
            char error[] = "Error en execvp\n";
            printf("el error pasa aqui: pid = %d\n", pid);
            write(STDERR_FILENO, error, sizeof(error) - 1);
            _exit(EXIT_FAILURE);
        } else {
            if (mode == 'S') {
                int status;
                wait(&status);
            }
        }
    }

    if (mode == 'C') {
        for (int i = 2; i < argc; i++) {
            int status;
            wait(&status);
        }
    }

    time_t end = time(NULL);
    time_t duration = end - start;
    char buffer[1024];
    int len = 0;
        len += write(STDOUT_FILENO, "Duración total: ", 16);

    int digits = 0;
    time_t temp_duration = duration;
    do {
        temp_duration /= 10;
        digits++;
    } while (temp_duration > 0);

    for (int i = digits - 1; i >= 0; i--) {
        buffer[i] = (duration % 10) + '0';
        duration /= 10;
    }
    write(STDOUT_FILENO, buffer, digits);
    write(STDOUT_FILENO, " segundos\n", 10);

    return 0;
}
