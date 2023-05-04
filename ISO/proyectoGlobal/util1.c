#include <stdio.h>
#include <stdlib.h>
#include <dirent.h>
#include <sys/stat.h>
#include <string.h>

#define MAX_NAME 512
#define BLOCK_SIZE 512

struct s_Registro
{
   char PathName[MAX_NAME];
   unsigned int Posicion;
   unsigned int Indice;
};

int main(int argc, char *argv[])
{
    DIR *dir;
    struct dirent *entry;
    struct stat fileStat;
    char filePath[MAX_NAME];
    FILE *resultadoFile, *tablaFile, *inputFile;
    struct s_Registro registro;
    unsigned int posicion = 0;
    size_t bytesRead;
    char buffer[BLOCK_SIZE];

    if (argc != 5) {
        printf("Uso: %s <directorio> <resultado.dat> <tabla.dat> <indice>\n", argv[0]);
        return 1;
    }

    dir = opendir(argv[1]);
    if (!dir) {
        perror("opendir");
        return 1;
    }

    resultadoFile = fopen(argv[2], "w");
    if (!resultadoFile) {
        perror("fopen");
        return 1;
    }

    tablaFile = fopen(argv[3], "w");
    if (!tablaFile) {
        perror("fopen");
        return 1;
    }

    while ((entry = readdir(dir)) != NULL) {
        snprintf(filePath, MAX_NAME, "%s/%s", argv[1], entry->d_name);
        if (stat(filePath, &fileStat) == -1) {
            perror("stat");
            return 1;
        }

        if (S_ISREG(fileStat.st_mode)) {
            strncpy(registro.PathName, filePath, MAX_NAME);
            registro.Posicion = posicion;
            registro.Indice = atoi(argv[4]);

            fwrite(&registro, sizeof(registro), 1, tablaFile);

            inputFile = fopen(filePath, "r");
            if (!inputFile) {
                perror("fopen");
                return 1;
            }

            while ((bytesRead = fread(buffer, 1, BLOCK_SIZE, inputFile)) > 0) {
                fwrite(buffer, 1, bytesRead, resultadoFile);
                posicion += bytesRead;

                // Rellenar con ceros si es necesario
                if (bytesRead < BLOCK_SIZE) {
                    memset(buffer, 0, BLOCK_SIZE - bytesRead);
                    fwrite(buffer, 1, BLOCK_SIZE - bytesRead, resultadoFile);
                    posicion += BLOCK_SIZE - bytesRead;
                }
            }

            fclose(inputFile);
        }
    }

    fclose(resultadoFile);
    fclose(tablaFile);
    closedir(dir);

    return 0;
}
