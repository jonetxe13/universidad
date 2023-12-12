/**********************************************************
	integral.c (SERIE) - integral de una funcion 
    adecuar el codigo de acuerdo al enunciado
***********************************************************/
#include <stdio.h>
#include <stdlib.h>
#include <time.h>
#include <math.h>
#include <omp.h>

double f (double x) {
  double  y; 
  y = 1.0 / (sin(x) + 2.0) + 1.0 / (sin(x)*cos(x) + 2.0);
  return (y); 
}

void Leer_datos (double *a, double *b, int *n) {
  float  x, y;
  printf ("\n Introduzca a, b (limites) y n (numero trapecios): \n");
  scanf  ("%f %f %d", &x, &y, n);
  (*a) = (double) (x);
  (*b) = (double) (y);
}

double Integrar (double a, double b, int n, double w) {
  double  resultado, x; 
  int     i; 
  resultado = (f(a) + f(b)) / 2.0; 
  #pragma omp parallel for private(x) reduction(+:resultado)
  for (i=1; i<n; i++) {
    x = a + i*w; 
    resultado += f(x); 
  } 
  resultado *=  w; 
  return (resultado);
}

int main () {
  double  a, b, w;    
  int     n;
  double error = 1e-11;

  double  tex;
  struct timespec  t0, t1;

  Leer_datos (&a, &b, &n);
  w = (b-a) / n;   

  clock_gettime (CLOCK_REALTIME, &t0);
  double resultado1 = Integrar (a, b, n, w);
  n = 2*n;
  w = (b-a) / n;   
  double resultado2 = Integrar (a, b, n, w);
  double errorRes = resultado2 - resultado1;
  int i = 1;
  // PARA COMPLETAR
  while ((fabs(errorRes) > error) && (i < 1000)){
    n = 2*n;
    w = (b-a) / n;   
    i++;
    resultado1 = resultado2;
    resultado2 = Integrar (a, b, n, w);
    errorRes = resultado2 - resultado1;
    printf("Res1: %lf; Res2: %lf\n", resultado1, resultado2);
    printf("%d\n", i);
    printf("%.12lf\n",errorRes);
  }

  clock_gettime (CLOCK_REALTIME, &t1);
  tex = (t1.tv_sec - t0.tv_sec) + (t1.tv_nsec - t0.tv_nsec) / (double)1e9;

  printf ("\n  Valor de la integral: %.12f;\n Y el valor de n al finalizar: %d\n; Y el valor de i: %d \n", resultado2, n, i);
  printf ("  Tiempo de ejecucion (serie) = %1.3f ms \n\n", tex*1000);
}
