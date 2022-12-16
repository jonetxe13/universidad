#include <stdio.h>
#include <omp.h>

#define N 4
#define M 100

int main(){
	int i, j, n, sum;

	// (0) en serie
	sum = 0;
	for(i=0; i<N; i++){
		for(j=0; j<M; j++){
			sum = sum + (i+j);
		}
	}
	printf("(0) sum = %d\n", sum);

	// (1) en serie
	sum = 0;
	for(n=0; n<N*M; n++){
		i = n / 100;
		j = n % 100;
		sum = sum + (i+j);
	}
	printf("(1) sum = %d\n", sum);

	// (2) N items para repartir
	sum = 0;
	#pragma omp parallel num_threads(4) private(i,j)
	{
		#pragma omp for reduction(+:sum)
		for(i=0; i<N; i++){
			for(j=0; j<M; j++){
				sum = sum + (i+j);
			}
		}
	}
	printf("(2) sum = %d\n", sum);

	// (3) N*M items para repartir
	sum = 0;
	#pragma omp parallel num_threads(4) private(i,j)
	{
		#pragma omp for collapse(2) reduction(+:sum)
		for(i=0; i<N; i++){
			for(j=0; j<M; j++){
				sum = sum + (i+j);
			}
		}
	}
	printf("(3) sum = %d\n", sum);

	// (4) N*M items para repartir
	sum = 0;
	#pragma omp parallel num_threads(4) private(i,j)
	{
		#pragma omp for reduction(+:sum)
		for(n=0; n<N*M; n++){
			i = n / 100;
			j = n % 100;
			sum = sum + (i+j);
		}
	}
	printf("(4) sum = %d\n", sum);
}
