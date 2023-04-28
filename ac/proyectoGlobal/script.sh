#!/bin/zsh
export OMP_NUM_THREADS=12
gcc -O2 $1.c -o $1 -lm -fopenmp

./$1 f1
