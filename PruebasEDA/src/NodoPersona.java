import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class NodoPersona {
	String nombre;
	LinkedList<NodoPersona> adyacentes;
	public NodoPersona(String nombre) {
		this.nombre = nombre;
		this.adyacentes = new LinkedList<NodoPersona>();
	}  
	/**
  * PRE: nomP está en el grafo
  * @param nomP Nombre de la persona fallecida
  * @param distMax Distancia máxima a la que se reparte la herencia
  * @return Lista de personas que no reciben herencia
  */
 public ArrayList<String> parientesEnfadados(String nomP, int distMax){
	Queue<NodoPersona> pila = new LinkedList<NodoPersona>();
	ArrayList<String> visitados = new ArrayList<String>();
	int quitarDistanciaONo = 2;
	pila.add(this);
	visitados.add(this.nombre);
	while(!pila.isEmpty()) {
		NodoPersona actual = pila.remove();
		quitarDistanciaONo--;
		for(NodoPersona ady: actual.adyacentes) {
			if(!visitados.contains(ady.nombre) && distMax>0) {
				visitados.add(ady.nombre);
				pila.add(ady);
				quitarDistanciaONo++;
			}
		}
		if(quitarDistanciaONo == 1)
			distMax--;
	}
	 return visitados;
 }
}
