/** PAR, Procesadores de Alto Rendimiento - GII (Ingenieria de Computadores)

    mcol.c  --  versión serie

     - paralelización de bucles
     - tipos de variables
     - paralelismo de grano fino y de grano grueso
     - reparto de iteraciones (scheduling)
***************************************************************************/
#include <stdio.h>
#include <time.h>
#include <stdlib.h>
#include <math.h>
#include <string.h>

#define  NFIL 200000
#define  NCOL 300
#define  MAXVAL 60

double  mat[NFIL][NCOL];

// valores iniciales de la matriz (al azar); no paralelizar
static void inicializar_mat (double mat[NFIL][NCOL]){
  int  i, j;
  for (i=0; i<NFIL; i++)
  for (j=0; j<NCOL; j++)
    mat[i][j] = rand () % MAXVAL - (j % 59);
}

// lectura del fichero de entrada; devuelve el número de peticiones
int leer_peticiones (char *fpet, int *columnas){
  FILE  *fich;
  int   i, x;
  fich = fopen (fpet, "r");
  i = x = 0;
  while (x != EOF) {
    x = fscanf (fich, "%d", columnas + i);
    i++;
  }
  fclose (fich);
  return (i-1);
}

// escritura del fichero de salida: resultados
void escribir_resultados (char *fsol, int npet, double *resultados){
  FILE  *fich;
  int   i;
  fich = fopen (fsol, "w");
  for (i=0; i<npet; i++)
    fprintf (fich, "%10.3f\n", resultados[i]);
  fclose (fich);
  return;
}

// "calculo": procesar las columnas de la matriz
void procesar_peticiones (double mat[NFIL][NCOL], int *columnas, int cant, double *resultados){
  int     i, j, columna;
  double  y, suma;
  for (j=0; j<cant; j++){
    columna = columnas[j];
    suma = 0.0;
    for (i=0; i<NFIL; i++){
      y = mat[i][columna];
      if (y > 0) suma += exp (y / 100); 
    }
    resultados[j] = suma;
  }
}

// programa principal
int main (int argc, char *argv[]){
  int     num_pet, columnas[NCOL];
  double  *resultados, Tej;
  struct timespec  t1, t0;
  char    fsol[20];

  if (argc != 2){
    printf ("\n Ejecutar: %s f1\n\n", argv[0]);
    return 0;
  }

  inicializar_mat (mat);
  num_pet = leer_peticiones (argv[1], columnas);
  resultados = malloc (num_pet * sizeof (double));

  clock_gettime (CLOCK_REALTIME, &t0);
  procesar_peticiones (mat, columnas, num_pet, resultados);
  clock_gettime (CLOCK_REALTIME, &t1);
  Tej = (t1.tv_sec - t0.tv_sec) + (t1.tv_nsec - t0.tv_nsec) / (double)1e9;
  
  strcpy (fsol, argv[1]);
  strcat (fsol, "_sol");
  escribir_resultados (fsol, num_pet, resultados);

  printf ("\n Peticiones: %d -- Ultimo resul.: %1.3f\n", num_pet, resultados[num_pet - 1]);
  printf (" Tej: %1.3f ms\n\n", Tej*1000);
  return (0);
}

