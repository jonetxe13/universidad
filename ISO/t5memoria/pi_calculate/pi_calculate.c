//////////////////
/// pi_calculate.c
//////////////////

// Formula of Pi = 4 * (1 - 1/3 + 1/5 - 1/7 + 1/9 - ... ) 

void function(double *t, double *k, double *l)
{
   (*t) += (*l)/(*k); 
   (*k) += 2.0; 
   (*l) *= -1.0; 
}
