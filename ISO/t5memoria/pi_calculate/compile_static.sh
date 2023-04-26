  # echo creating object file for building the static library
  echo gcc -c pi_calculate.c -o pi_calculate_static.o
  gcc -c pi_calculate.c -o pi_calculate_static.o

  # echo creating the static library
  echo ar rcs libfunctionstatic.a pi_calculate_static.o 
  ar rcs libfunctionstatic.a pi_calculate_static.o 

  # echo creating the test program with the static library
  echo gcc -static pi_test.c -L. -lfunctionstatic -o pi_test_static
  gcc -static pi_test.c -L. -lfunctionstatic -o pi_test_static

  ls -l

  #echo tests
  echo testing the static version
  time ./pi_test_static 30000000
  time ./pi_test_static 30000000
  time ./pi_test_static 30000000
