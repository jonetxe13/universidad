#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <dirent.h>
#include <sys/stat.h>
#include <unistd.h>
#include <fcntl.h>

#define MAX_NAME 512
#define BLOCK_SIZE 512

struct s_Registro {
    char PathName[MAX_NAME];
    unsigned int Posicion;
    unsigned int Indice;
};

void write_data(int result_fd, int table_fd, char *file_path, unsigned int index) {
    int input_fd = open(file_path, O_RDONLY);
    if (input_fd == -1) {
        printf("Error opening file %s\n", file_path);
        return;
    }

    // Get the current position in the result file
    unsigned int position = lseek(result_fd, 0, SEEK_CUR);

    // Write data from input file to result file
    char buffer[BLOCK_SIZE];
    ssize_t bytes_read;
    size_t total_bytes_written = 0;
    while ((bytes_read = read(input_fd, buffer, BLOCK_SIZE)) > 0) {
        write(result_fd, buffer, bytes_read);
        total_bytes_written += bytes_read;
    }

    // Pad with zeros to align to block size
    size_t padding = BLOCK_SIZE - (total_bytes_written % BLOCK_SIZE);
    if (padding < BLOCK_SIZE) {
        memset(buffer, 0, padding);
        write(result_fd, buffer, padding);
        total_bytes_written += padding;
    }

    // Write entry to table file
    struct s_Registro entry;
    strncpy(entry.PathName, file_path, MAX_NAME);
    entry.Posicion = position;
    entry.Indice = index;
    write(table_fd, &entry, sizeof(entry));

    close(input_fd);
}

void process_directory(char *dir_path, char *result_path, char *table_path, unsigned int index) {
    DIR *dir = opendir(dir_path);
    if (dir == NULL) {
        printf("Error opening directory %s\n", dir_path);
        return;
    }

    int result_fd = open(result_path, O_WRONLY | O_CREAT | O_TRUNC, 0666);
    if (result_fd == -1) {
        printf("Error opening result file %s\n", result_path);
        closedir(dir);
        return;
    }

    int table_fd = open(table_path, O_WRONLY | O_CREAT | O_TRUNC, 0666);
    if (table_fd == -1) {
        printf("Error opening table file %s\n", table_path);
        close(result_fd);
        closedir(dir);
        return;
    }

    struct dirent *entry;
    while ((entry = readdir(dir)) != NULL) {
        if (entry->d_type == DT_REG) {
            char file_path[MAX_NAME];
            snprintf(file_path, MAX_NAME, "%s/%s", dir_path, entry->d_name);
            write_data(result_fd, table_fd, file_path,index);
        }
    }

    close(table_fd);
    close(result_fd);
    closedir(dir);
}

int main(int argc, char **argv) {
    if (argc != 5) {
        printf("Usage: %s <directory> <result file> <table file> <index>\n", argv[0]);
        return 1;
    }

    char *dir_path = argv[1];
    char *result_path = argv[2];
    char *table_path = argv[3];
    unsigned int index = atoi(argv[4]);

    process_directory(dir_path, result_path, table_path,index);

    return 0;
}

