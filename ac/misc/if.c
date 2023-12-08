#include <stdio.h>
#include <omp.h>

#define N 5

int main(){
	int tid, i, j, sum, exec;

	if(N>=10)
		exec = 1;
	else
		exec = 0;

	sum = 0;
	#pragma omp parallel if(exec) \
					num_threads(4) private(i,j)
	{
		tid = omp_get_thread_num();
		if(tid==0){
			printf("[+] Total number of threads: %d\n", omp_get_num_threads());
		}
		#pragma omp for reduction(+:sum)
		for(i=0; i<N; i++){
			for(j=0; j<10; j++){
				sum = sum + (i+j);
			}
		}
	}
	printf("[+] sum = %d\n", sum);
}
