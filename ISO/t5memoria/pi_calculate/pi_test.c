/////////////
/// pi_test.c
///
/// This program calculates the value of Pi, using the formula
///  
/// Pi = 4 * (1 - 1/3 + 1/5 - 1/7 + 1/9 - ... ) 
///
/// Usage: pi_test iteration_number
///
///////////////////////////////////

#include <stdio.h>
#include <math.h>
#include <stdlib.h>

#include "pi_calculate.h"
  
int main(int argc, char *argv[])
{
   double t, k = 3.0, l = -1.0;
   int i, s;

   if (argc < 2) {
      fprintf(stderr, "%s <iteration number>\n", argv[0]);
      exit(1);
   }
       
   t = 1.0; 
   for(i = 0, s = atoi(argv[1]); i < s; i++) {
      function(&t, &k, &l);
   }
   t *= 4; 

   printf("My value of Pi: %1.16f,  Value of Pi in <math.h>: %.16f\n", t, M_PI); 
   printf("Absolute difference: %.16f\n", fabs(M_PI - t)); 
}
