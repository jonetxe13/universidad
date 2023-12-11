#!/bin/bash

# Compila los programas
gcc -O2 -o pitagoras -fopenmp pitagoras.c -lm
gcc -O2 -o reparto_it -fopenmp reparto_it.c -lm
gcc -O2 -o integral -fopenmp integral.c -lm

# Nombre de los archivos para guardar los resultados
pitagoras_output="resultados_pitagoras.txt"
reparto_it_output="resultados_reparto_it.txt"
integral_output="resultados_integral.txt"

# Función para ejecutar un programa y guardar los resultados
run_program() {
    local program=$1
    local threads=$2
    local input_param=$3
    local output_file=$4

    echo "Programa $program:"
    echo "Hilos: $threads" >> "$output_file"

    if [ "$program" == "integral" ]; then
        # Si el programa es integral.c, entonces redirige la entrada desde el archivo
        ( echo "0 100 10" | time ./$program > /dev/null; ) 2>> "$output_file"
    else
        # Si el programa no es integral.c, simplemente ejecuta el programa sin redirigir la entrada
        (time ./$program $input_param > /dev/null; ) 2>> "$output_file"
    fi

    echo "---------------------" >> "$output_file"
}
# Programa pitagoras
for num_threads in 1 2 4 8 16 32; do
    for N in 100 1000 5000 10000 50000; do
        export OMP_NUM_THREADS=$num_threads
        run_program "pitagoras" $num_threads $N "$pitagoras_output"
    done
done

# Programa reparto_it
for num_threads in 1 2 4 8 16 32; do
    export OMP_NUM_THREADS=$num_threads
    run_program "reparto_it" $num_threads "" "$reparto_it_output"
done

# Programa integral
for num_threads in 1 2 4 8 16 32; do
    export OMP_NUM_THREADS=$num_threads
    run_program "integral" $num_threads "" "$integral_output"
done

echo "Resultados guardados en archivos respectivos"

