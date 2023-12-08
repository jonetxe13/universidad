#include <stdio.h>
#include <omp.h>

int x;

int main(){
	int tid;
	#pragma omp threadprivate(x)
	x = 0;

	#pragma omp parallel num_threads(4) \
					private(tid) copyin(x)
	{
		tid = omp_get_thread_num();
		x = x+tid;
		printf("tid=%d: x=%d\n", tid, x);
		if(tid==0){ // master
			x = 10;
		}
	}
	printf("\n");

	#pragma omp parallel num_threads(4) \
					private(tid) copyin(x)
	{
		tid = omp_get_thread_num();
		x = x+tid;
		printf("tid=%d: x=%d\n", tid, x);
	}
	printf("\n");
}
