  # echo creating object file for building the dynamic library
  echo gcc -c -fPIC pi_calculate.c -o pi_calculate_dynamic.o
  gcc -c -fPIC pi_calculate.c -o pi_calculate_dynamic.o

  # echo creating the dynamic-linked library
  echo gcc -shared -Wl,-soname,libfunction.so.1 -o libfunction.so.1.0 pi_calculate_dynamic.o -lc
  gcc -shared -Wl,-soname,libfunction.so.1 -o libfunction.so.1.0 pi_calculate_dynamic.o -lc

  # set up the soname
  # ln -sf libfunction.so.1.0 libfunction.so.1 
  # but let's let ldconfig figure it out
  /sbin/ldconfig -n .

  # Set up the linker name.
  # In a more sophisticated setting, we'd need to make
  # sure that if there was an existing linker name,
  # and if so, check if it should stay or not.
  ln -sf libfunction.so.1 libfunction.so 

  # Compile program file
  echo gcc pi_test.c -o pi_test_dynamic -L. -lfunction
  gcc pi_test.c -o pi_test_dynamic -L. -lfunction

  ls -l

  # Set environment variable for finding the library at execution time
  export LD_LIBRARY_PATH=.:$LD_LIBRARY_PATH

  echo testting the dynamic version
  time ./pi_test_dynamic 30000000
  time ./pi_test_dynamic 30000000
  time ./pi_test_dynamic 30000000
