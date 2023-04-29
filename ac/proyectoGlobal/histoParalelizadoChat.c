/** PAR, Procesadores de Alto Rendimiento - GII (Ingenieria de Computadores)
 histo.c -- versión serie
 - paralelización de bucles
 - tipos de variables
 - paralelismo de grano fino y de grano grueso
 - reparto de iteraciones (scheduling)
***************************************************************************/
#include <stdio.h>
#include <stdlib.h>
#define N 5000
#define MAX 10 

int mat[N][N];

int main (){
 int i, j, k, histo[MAX], hmin, imin;
 int suma_fil[N], suma_col[N];
 // valores iniciales, NO paralelizar
 for(i=0; i<N; i++)
 for(j=0; j<N; j++) {
   if (i%3) mat[i][j] = (i+j) % MAX;
   else mat[i][j] = (i+i*j) % MAX;
 }
 for (i=0; i<MAX; i++) histo[i] = 0;

 for (i=0; i<N; i++){
   suma_fil[i] = 0;
   suma_col[i] = 0;
 }

// ========================
// === PARA PARALELIZAR ===
// ========================
#pragma omp parallel
{
  int local_histo[MAX];
  for (int i = 0; i < MAX; i++)
    local_histo[i] = 0;

  #pragma omp for nowait schedule(dynamic, 50)
  for (int i=0; i<N; i++)
    for (int j=0; j<N; j++)
      // #pragma omp atomic
      local_histo[mat[i][j]]++; // histograma
    
  #pragma omp critical
  for (int i = 0; i < MAX; i++)
    histo[i] += local_histo[i];

  #pragma omp for nowait schedule(dynamic, 50)
  for (int i=0; i<N; i++) // suma de las filas de la matriz
    for (int j=0; j<N; j++)
      suma_fil[i] += mat[i][j];

  #pragma omp for nowait schedule(dynamic, 50)
  for (int j=0; j<N; j++) // suma de las columnas de la matriz
    for (int i=0; i<N; i++)
      suma_col[j] += mat[i][j];
}

hmin = N*N + 1;
#pragma omp parallel for reduction(min:hmin)
for (int i=0; i<MAX; i++){ // el valor minimo de la histograma y su posicion
  if (hmin > histo[i]){
    hmin = histo[i];
    imin = i;
  }
}

// === FIN DE LA REGION PARALELA ===
  
 printf ("\n Histograma de la matriz mat[%d][%d] (con %d valores diferentes)\n", N, N, MAX);
 printf (" ===========================================================\n");
 for (k=0; k<MAX; k++) printf ("%9d", k);
 printf ("\n");
 for (k=0; k<MAX; k++) printf ("%9d", histo[k]);
 printf ("\n");
 printf ("\n El minimo de la histograma: %d, posicion %d\n", hmin, imin);
 printf ("\n La suma de la fila 100: %d ", suma_fil[100]);
 printf ("\n La suma de la columna 200: %d \n\n", suma_col[200]);

}
