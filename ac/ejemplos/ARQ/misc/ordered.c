#include <stdio.h>
#include <omp.h>
#include <unistd.h>

#define N 10

int main(){
	int i, myval;
	#pragma omp parallel num_threads(4) \
			private(i, myval)
	{
		#pragma omp for ordered
		for(i=1; i<=N; i++){
			myval = i*i % 5;
			sleep(myval);
			#pragma omp ordered
			{
				printf("%d %d\n", i, myval);
			}
		}
	}
}
