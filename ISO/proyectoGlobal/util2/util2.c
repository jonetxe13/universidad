#include <unistd.h>
#include <fcntl.h>
#include <sys/stat.h>
#include <dirent.h>

#define MAX_NAME 512

void write_str(int fd, char *str) {
    size_t len = 0;
    while (str[len] != '\0') {
        len++;
    }
    write(fd, str, len);
}

void process_directory(char *dir_path) {
    DIR *dir = opendir(dir_path);
    if (dir == NULL) {
        write_str(STDERR_FILENO, "Error opening directory\n");
        return;
    }

    struct dirent *entry;
    while ((entry = readdir(dir)) != NULL) {
        char file_path[MAX_NAME];
        int file_path_len = 0;
        while (dir_path[file_path_len] != '\0') {
            file_path[file_path_len] = dir_path[file_path_len];
            file_path_len++;
        }
        file_path[file_path_len] = '/';
        file_path_len++;
        for (int i = 0; i < entry->d_reclen; i++) {
            file_path[file_path_len + i] = entry->d_name[i];
        }
        file_path_len += entry->d_reclen;
        file_path[file_path_len] = '\0';

        struct stat file_stat;
        if (fstatat(dirfd(dir), entry->d_name, &file_stat, 0) == -1) {
            write_str(STDERR_FILENO, "Error getting file status\n");
            continue;
        }

        mode_t new_mode;
        if (S_ISREG(file_stat.st_mode)) {
            // Regular file
            new_mode = file_stat.st_mode & S_IRWXU;
        } else if (S_ISDIR(file_stat.st_mode)) {
            // Directory
            new_mode = file_stat.st_mode | S_IXUSR | S_IXGRP;
            new_mode &= ~(S_IROTH | S_IXOTH);
        } else if (S_ISCHR(file_stat.st_mode) || S_ISBLK(file_stat.st_mode)) {
            // Character or block device
            new_mode = file_stat.st_mode & S_IRWXU;
        } else {
            // Other file type
            continue;
        }

        if (fchmodat(dirfd(dir), entry->d_name, new_mode, 0) == -1) {
            write_str(STDERR_FILENO, "Error changing permissions\n");
        }
    }

    closedir(dir);
}

int main(int argc, char **argv) {
    if (argc != 2) {
        write_str(STDERR_FILENO, "Usage: program <directory>\n");
        return 1;
    }

    char *dir_path = argv[1];

    process_directory(dir_path);

    return 0;
}

