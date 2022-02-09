package packNumbers;

import java.util.Scanner;

public class DEMO {

	public static void main(String[] args) {
		WorkingWithNumbers nuevo = new WorkingWithNumbers();
		Scanner consola = new Scanner(System.in);
		int n = consola.nextInt (); 
		System.out.println(nuevo.isEven(n));
		nuevo.evenValues(n);
		consola.close ();
	}
}
