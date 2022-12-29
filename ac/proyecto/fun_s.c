/*
    AC - OpenMP -- SERIE
    fun_s.c
     rutinas que se utilizan en el modulo gengrupos_s.c 
****************************************************************************/
#include <math.h>
#include <float.h> // DBL_MAX
#include <stdlib.h>
#include <omp.h>
#include <stdio.h>

#include "defineg.h"           // definiciones

/**************************************************************************************
   1 - Funcion para calcular la distancia genetica entre dos elementos (distancia euclidea)
       Entrada:  2 elementos con NCAR caracteristicas (por referencia)
       Salida:  distancia (double)
**************************************************************************************/
double gendist (float *elem1, float *elem2)
{
  // PARA COMPLETAR
  // calcular la distancia euclidea entre dos vectores
  double termino = 0;
  /* #pragma omp for reduction(+:termino) */
  for(int i = 0; i < NCAR; i++){
    termino += pow((elem1[i] - elem2[i]), 2);
  }
	return sqrt(termino);
}

/****************************************************************************************
   2 - Funcion para calcular el grupo (cluster) mas cercano (centroide mas cercano)
   Entrada:  nelem  numero de elementos, int
             elem   elementos, una matriz de tamanno MAXE x NCAR, por referencia
             cent   centroides, una matriz de tamanno NGRUPOS x NCAR, por referencia
   Salida:   popul  grupo mas cercano a cada elemento, vector de tamanno MAXE, por referencia
*****************************************************************************************/
void grupo_cercano (int nelem, float elem[][NCAR], float cent[][NCAR], int *popul)
{
  /* #pragma omp parallel */
  /* { */
  /*   #pragma omp for */ 
  	for (int i = 0; i < nelem; i++) {
      double minDist = DBL_MAX; // Distancia mínima inicializada en infinito
      int minIdx = 0; // Índice del grupo más cercano
      for (int j = 0; j < ngrupos; j++) {
        double dist = gendist(elem[i], cent[j]); // Calcular distancia euclidea
        if (dist < minDist) { // Actualizar distancia mínima y índice
          minDist = dist;
          minIdx = j;
        }
      }
      popul[i] = minIdx; // Asignar grupo más cercano al elemento i-ésimo
    }
  /* } */
}

/****************************************************************************************
   3 - Funcion para calcular la calidad de la particion de clusteres.
       Ratio entre a y b. El termino a corresponde a la distancia intra-cluster.
       El termino b corresponde a la distancia inter-cluster.
   Entrada:  elem     elementos, una matriz de tamanno MAXE x NCAR, por referencia
             listag   vector de NGRUPOS structs (informacion de grupos generados), por ref.
             cent     centroides, una matriz de tamanno NGRUPOS x NCAR, por referencia
   Salida:   valor del CVI (double): calidad/ bondad de la particion de clusters
*****************************************************************************************/
double silhouette_simple(float elem[][NCAR], struct lista_grupos *listag, float cent[][NCAR], float a[]){
    // PARA COMPLETAR

	// aproximar a[i] de cada cluster: calcular la densidad de los grupos
    //		media de las distancia entre todos los elementos del grupo
    //   	si el numero de elementos del grupo es 0 o 1, densidad = 0
  float b[ngrupos];
  float s[ngrupos];
  float S;
  #pragma omp parallel
  {
    #pragma omp for
    for (int i = 0; i <MAX_GRUPOS; i++) {
      if (listag[i].nelemg < 2) {
        a[i] = 0;
        continue;
      }
  
      // calcular la distancia media entre todos los elementos del grupo
      float dist = 0;
      for (int j = 0; j < listag[i].nelemg; j++) {
        for (int k = j+1; k < listag[i].nelemg; k++) {
          dist += gendist(elem[listag[i].elemg[j]], elem[listag[i].elemg[k]]);
        }
      }
      a[i] = dist / (listag[i].nelemg * (listag[i].nelemg));
    }
  
    // aproximar b[i] de cada cluster
    #pragma omp for
    for (int i = 0; i < ngrupos; i++) {
      b[i] = FLT_MAX; // inicializar con el valor máximo de float
      
      float dist = 0;
      for (int j = i+1; j < ngrupos; j++){
        dist += gendist(cent[i], cent[j]);
      }
  
      b[i] = dist / ((ngrupos*NCAR) - 1);
    }
      // aproximar b[i] de cada cluster
  	
  	// calcular el ratio s[i] de cada cluster
    #pragma omp for
    for(int i = 0; i < ngrupos; i++){
      if(a[i] >= b[i]) s[i] = (b[i] - a[i]) / (a[i]);
      else{ s[i] = (b[i] - a[i]) / (b[i]); }
    }
    
    	// promedio y devolver
    #pragma omp for
    for(int i = 0; i < ngrupos; i++){
      S += s[i];
    }
      /* printf("\nLa variable S da este valor: "); */
      /* printf("%f", S); */
  }
  return S/(ngrupos);
}

/********************************************************************************************
   4 - Funcion para relizar el analisis de enfermedades
   Entrada:  listag   vector de NGRUPOS structs (informacion de grupos generados), por ref.
             enf      enfermedades, una matriz de tamaño MAXE x TENF, por referencia
   Salida:   prob_enf vector de TENF structs (informacion del análisis realizado), por ref.
*****************************************************************************************/
void analisis_enfermedades (struct lista_grupos *listag, float enf[][TENF], struct analisis *prob_enf){
	// PARA COMPLETAR
	// Realizar el análisis de enfermedades en los grupos:
	//		mediana máxima y el grupo en el que se da este máximo (para cada enfermedad)
	//		mediana mínima y su grupo en el que se da este mínimo (para cada enfermedad)

  int i, j;
  float max, min;
  int max_group, min_group;


  #pragma omp parallel // este tambien es algo peor wtf
  {
    #pragma omp for
    //ordenar los elementos de cada grupo
    for(int i = 0; i < ngrupos; i++){
      for(int j = 0; j < listag[i].nelemg; j++){
        for(int indiceActual = 0; indiceActual < listag[i].nelemg - 1; indiceActual++){
          int indiceSiguiente = indiceActual+1;
          if(listag[i].elemg[indiceActual] > listag[i].elemg[indiceSiguiente]){
            int aux = listag[i].elemg[indiceActual];
            listag[i].elemg[indiceActual] = listag[i].elemg[indiceSiguiente];
            listag[i].elemg[indiceSiguiente] = aux;
          }
        }
      }
    }


  #pragma omp for
    //buscar la mediana dentro de cada grupo
    for (i = 0; i < TENF; i++){
      max = enf[0][i];
      min = enf[0][i];
      max_group = 0;
      min_group = 0;
  
      for (j = 0; j < listag->nelemg; j++){
          if (enf[listag->elemg[j]][i] > max){
              max = enf[listag->elemg[j]][i];
              max_group = j;
          }
  
          if (enf[listag->elemg[j]][i] < min){
              min = enf[listag->elemg[j]][i];
              min_group = j;
          }
      }
  
      prob_enf[i].mmax = max;
      prob_enf[i].gmax = max_group;
      prob_enf[i].mmin = min;
      prob_enf[i].gmin = min_group;
    }
  }
  /* for(int i = 0; i < ngrupos; i++){ */
  /*   for(int j = 0; j < listag[0].nelemg; j++){ */
  /*     printf("%d", listag[0].elemg[j]); */
  /*     printf(" "); */
  /*   } */
  /*   printf("\n\n\n\n"); */
  /* } */
}



/***************************************************************************************************
   OTRAS FUNCIONES DE LA APLICACION
****************************************************************************************************/

void inicializar_centroides(float cent[][NCAR]){
	int i, j;
	srand (147);
	for (i=0; i<ngrupos; i++)
		for (j=0; j<NCAR/2; j++){
			cent[i][j] = (rand() % 10000) / 100.0;
			cent[i][j+(NCAR/2)] = cent[i][j];
		}
}

int nuevos_centroides(float elem[][NCAR], float cent[][NCAR], int popul[], int nelem){
	int i, j, fin;
	double discent;
	double additions[ngrupos][NCAR+1];
	float newcent[ngrupos][NCAR];

	for (i=0; i<ngrupos; i++)
		for (j=0; j<NCAR+1; j++)
			additions[i][j] = 0.0;

	// acumular los valores de cada caracteristica (100); numero de elementos al final
	for (i=0; i<nelem; i++){
		for (j=0; j<NCAR; j++) additions[popul[i]][j] += elem[i][j];
		additions[popul[i]][NCAR]++;
	}

	// calcular los nuevos centroides y decidir si el proceso ha finalizado o no (en funcion de DELTA)
	fin = 1;
	for (i=0; i<ngrupos; i++){
		if (additions[i][NCAR] > 0) { // ese grupo (cluster) no esta vacio
			// media de cada caracteristica
			for (j=0; j<NCAR; j++)
				newcent[i][j] = (float)(additions[i][j] / additions[i][NCAR]);

			// decidir si el proceso ha finalizado
			discent = gendist (&newcent[i][0], &cent[i][0]);
			if (discent > DELTA1)
				fin = 0;  // en alguna centroide hay cambios; continuar

			// copiar los nuevos centroides
			for (j=0; j<NCAR; j++)
				cent[i][j] = newcent[i][j];
		}
	}
	return fin;
}

