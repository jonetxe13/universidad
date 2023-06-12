import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Scanner;

public class HashMap2 extends HashMap {
	public HashMap2() {
		super();
	}
	public HashMap<String, Integer> contarApariciones(String nomFich) throws FileNotFoundException{
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		Scanner sc = new Scanner(new File(nomFich));
		String palabra;
		while(sc.hasNext()) {
			palabra = sc.next().toLowerCase();
			if(hm.containsKey(palabra)) {
				int clave = hm.get(palabra);
				hm.replace(palabra, clave+1);
				
			}
			else {
				hm.put(palabra, 1);
			}
		}
		return hm;
	}//perfecto mi loco y eso que no me acordaba de como se usaba esto xd
}