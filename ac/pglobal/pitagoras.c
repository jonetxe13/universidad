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

int main (int argc, char *argv[]){
  int N = atoi (argv[1]);

  // PARA COMPLETAR Y LUEGO PARALELIZAR
  // calcula los cuadrados de los primeros N numeros

  // suma todos los pares y comprueba uno por uno si son cuadrados o no
  // cuenta el numero de cuadrados encontrados
  // imprime los pares (para tomar tiempos comenta los printf-s)

  // al final, imprime el numero de pares y N
}
