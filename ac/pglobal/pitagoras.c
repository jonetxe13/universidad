/************************************************
    Arquitectura de COmputadores (AC)
    Laboratorio OpenMP

    pitagoras.c -- version en serie

    para compilar:  annade -lm al final
    para ejecutar:  indica el parametro N
*************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <math.h>      // OJO al compilar, -lm

int cuadrado (int num){
  int  x = (int) floor (sqrt ((double) num));

  if (x*x == num) return (1);	// es cuadrado
  else return (0);
}

int main(int argc, char *argv[]) {

  if (argc != 2) {
      fprintf(stderr, "Uso: %s <N>\n", argv[0]);
      exit(EXIT_FAILURE);
  }

  int N = atoi(argv[1]);

  // PARA COMPLETAR Y LUEGO PARALELIZAR
  // calcula los cuadrados de los primeros N numeros

  // suma todos los pares y comprueba uno por uno si son cuadrados o no
  // cuenta el numero de cuadrados encontrados
  // imprime los pares (para tomar tiempos comenta los printf-s)

  // al final, imprime el numero de pares y N

  // Calcula los cuadrados de los primeros N números
  int *cuadrados = (int *)malloc(N * sizeof(int));
  for (int i = 0; i < N; ++i) {
      cuadrados[i] = (i + 1) * (i + 1);
  }

  // Suma todos los pares y comprueba si la suma es cuadrado
  int count = 0;
  #pragma omp parallel for schedule(static) reduction(+:count)
  for (int i = 0; i < N; ++i) {
      for (int j = i; j < N; ++j) {
          int suma = cuadrados[i] + cuadrados[j];
          if (cuadrado(suma)) {
              #pragma omp critical
              {
                  // Imprime los pares encontrados
                  printf("(%d, %d)\n", i + 1, j + 1);
              }
              count++;
          }
      }
  }

  // Imprime el número de pares y N
  printf("Número de pares encontrados: %d\n", count);
  printf("N: %d\n", N);

  free(cuadrados);
  return 0;
}
