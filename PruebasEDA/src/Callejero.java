import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Callejero {
	private int numVertices;
	private ArrayList<NodoCalle> calles;
	
	
	public Callejero(int numVertices, ArrayList<NodoCalle> calles) {
		this.numVertices=numVertices;
		this.calles=calles;
	}
	//Devuelve true si hay un camino que une el nodo origen y el nodo destino, si no false.
	//Pre: ambos nodos están en el grafo
	public boolean hayCamino(NodoCalle origen, NodoCalle destino) {
		Queue<NodoCalle> pila = new LinkedList<NodoCalle>();
		pila.add(origen);
		while(!pila.isEmpty()) {
			NodoCalle actual = pila.poll();
			if(actual.nomCalle.equals(destino.nomCalle)) return true;
			else {
			for(NodoCalle nodo: actual.enlaces) {
				if(nodo.equals(destino)) return true;
				else {
					pila.add(nodo);
				}
			}
			}
		}
		return false;
	}
	//Devuelve true si hay un camino que une la calle con nombre nomOrigen y la calle
	// con nombre nomDestino, si no false.
	//Pre: ambas calles están en el grafo
	public boolean hayCamino2(String nomOrigen, String nomDestino) {
		Queue<NodoCalle> pila = new LinkedList<NodoCalle>();
		for(NodoCalle nodo: calles) {
			if(nodo.nomCalle.equals(nomOrigen)) pila.add(nodo);
		}
		while(!pila.isEmpty()) {
			NodoCalle actual = pila.poll();
			for(NodoCalle nodo: actual.enlaces) {
				if(nodo.nomCalle.equals(nomDestino)) return true;
				else {
					pila.add(nodo);
				}
			}
		}
		return false;
	}
	//Devuelve la longitud del camino más corto entre el nodo origen y el nodo destino.
	//Ejemplo: la distancia entre el nodo A y el nodo J es 3
	//Pre: ambos nodos están en el grafo
	public int calcularDistancia(NodoCalle origen, NodoCalle destino) {
		
		return 0;
	}
	//Devuelve una lista con los nombres de las calles que hay en el camino más corto
	// entre el nodo origen y el nodo destino (ambos incluidos).
	//Ejemplo: las calles entre el nodo A y el nodo J son <A, C, G, J>
	//Pre: ambos nodos están en el grafo
	public LinkedList<String> obtenerCamino(NodoCalle origen, NodoCalle destino) {
		
		return null;
	}
}
