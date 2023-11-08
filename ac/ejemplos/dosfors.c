/*  AC, Arquitectura de Computadores - Inenieria Informatica, curso 2
    Laboratorio OpenMP

    dosfors.c   PARA PARALELIZAR

    Para paralelizar el bucle de dos dimensiones: i o j
    No hace nada, simular un calculo mediante usleep
    Ejecuta con 1, 4, 8, 16 y 32 hilos; analiza los tiempos de ejecucion
***********************************************************************/

#include <stdio.h>
#include <unistd.h>
#include <time.h>

#ifdef _OPENMP
  #include <omp.h>
#else
  #define omp_get_thread_num()  0
  #define omp_get_max_threads() 1
#endif


#define N1 8
#define N2 32


int fun (int x)
{
  usleep(5000);
  return (x*x);
}


int main ()
{
  int     i, j, A[N1][N2], suma;
  double  tex;
  struct timespec  t0, t1;


  for (i=0; i<N1; i++)
  for (j=0; j<N2; j++)
    A[i][j] = (i + j) % 13;


  clock_gettime(CLOCK_REALTIME, &t0);

  // este es el bucle a paralelizar
  for (i=0; i<N1; i++)
   for (j=0; j<N2; j++)
   {
      suma += fun (A[i][j]);
   }


  clock_gettime (CLOCK_REALTIME, &t1);
  tex = (t1.tv_sec - t0.tv_sec) + (t1.tv_nsec - t0.tv_nsec) / (double)1e9;
  printf ("\n suma =  %d \n", suma);
  printf ("\n Tex. (%d hilos) = %1.3f ms\n", omp_get_max_threads (), tex*1000);
}

