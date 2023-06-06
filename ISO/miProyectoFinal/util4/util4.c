#include <stdio.h>
#include <sys/wait.h>
#include <stdlib.h>
#include <unistd.h>
#include <string.h>

#define MAX_USERS 100

struct user_count {
    char name[32];
    int count;
};

int main(int argc, char *argv[]) {
    int pipefd[2];
    pid_t cpid;
<<<<<<< HEAD
=======
    char buf;
>>>>>>> e06de3fb815f0b5959863d2783fee4e128b2e675
    FILE *stream;
    char line[256];
    char user[32];
    struct user_count users[MAX_USERS];
    int num_users = 0;
    int i, j;
<<<<<<< HEAD
    
    if(argc < 2){
        printf("Uso: %s usuario1 usuario2 ... usuarioN", argv[0]);
	exit(EXIT_FAILURE);
    }
=======

>>>>>>> e06de3fb815f0b5959863d2783fee4e128b2e675
    memset(users, 0, sizeof(users));

    if (pipe(pipefd) == -1) {
        perror("pipe");
        exit(EXIT_FAILURE);
    }

    cpid = fork();
    if (cpid == -1) {
        perror("fork");
        exit(EXIT_FAILURE);
    }

    if (cpid == 0) { /* Child */
        close(pipefd[0]); /* Close unused read end */
        dup2(pipefd[1], STDOUT_FILENO); /* Redirect stdout to write end of pipe */
        close(pipefd[1]); /* Close original write end of pipe */
        execlp("last", "last", NULL); /* Execute last command */
        perror("execlp");
        exit(EXIT_FAILURE);
    } else { /* Parent */
        close(pipefd[1]); /* Close unused write end */
        stream = fdopen(pipefd[0], "r"); /* Open read end of pipe as a stream */
        while (fgets(line, sizeof(line), stream) != NULL) {
            sscanf(line, "%s", user);
            for (i = 0; i < num_users; i++) {
	  	if (strcmp(users[i].name, user) == 0) {
                    users[i].count++;
                    break;
		}
            }
            if (i == num_users) {
                strncpy(users[num_users].name, user, sizeof(users[num_users].name) - 1);
                users[num_users].count = 1;
                num_users++;
            }
        }
        fclose(stream);
        wait(NULL); /* Wait for child to terminate */
    }
    for (i = 1; i < argc; i++) {
        for (j = 0; j < num_users; j++) {
            if (strcmp(argv[i], users[j].name) == 0) {
                printf("%s: %d\n", users[j].name, users[j].count);
                break;
            }
        }
    }
    return 0;
}
