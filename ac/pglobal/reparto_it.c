/***************************************
    Arquitectura de Computadores
    OpenMP laborategia

    reparto_it.c  --  PARA PARALELIZAR
*****************************************/
#include <stdlib.h>
#include <stdio.h>
#include <unistd.h>
#include "omp.h"
#define N 100
#define LOTE 6

void fun (int x){
  usleep (100*(x%15));
}

int main () {
  int  i, j, A[N], B[N];
  int  tid = -1;

  // Bucle 1 para paralelizar: reparto estatico consecutivo
 #pragma omp parallel for private(i, tid) 
  for (i=0; i<N; i++) 
  {  
    tid = omp_get_thread_num();
    fun (i);
    A[i] = tid;
  }

  // Bucle 2 para paralelizar: reparto dinamico, chunk scheduling
  // lote (chuck) de 6 iteraciones (LOTE)
 #pragma omp parallel for private(i, tid) schedule(dynamic, LOTE)
  for (i=0; i<N; i++) 
  {  
    tid = omp_get_thread_num();
    fun (i);
    B[i] = tid;
  }

  // imprimir vectores como matrices de 10 x 10
  printf ("\n\n Vector A\n");
  for (i=0; i<10; i++) {
    for (j=0; j<10; j++) printf ("%3d", A[10*i+j]);
    printf ("\n");
  }

  printf ("\n\n Vector B\n");
  for (i=0; i<10; i++) {
    for (j=0; j<10; j++) printf ("%3d", B[10*i+j]);
    printf ("\n");
  }

  return (0);
}
