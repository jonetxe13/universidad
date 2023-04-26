  echo testing static version...
  time ./pi_test_static 600000000
  time ./pi_test_static 600000000
  time ./pi_test_static 600000000

  export LD_LIBRARY_PATH=.:$LD_LIBRARY_PATH
  echo testing dynamic version...
  time ./pi_test_dynamic 600000000
  time ./pi_test_dynamic 600000000
  time ./pi_test_dynamic 600000000
