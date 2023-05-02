#!/bin/zsh
gcc -O2 -o gengrupos_s gengrupos_s.c fun_s.c -lm
export OMP_NUM_THREADS=12
echo "el numero para ejecutar el programa es: " $@
./gengrupos_s dbgen.dat dbenf.dat $@
