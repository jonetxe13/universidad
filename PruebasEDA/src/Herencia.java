import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Queue;

public class Herencia {
    private int numVertices;
    private ArrayList<NodoPersona> personas;
    public Herencia(int numV, ArrayList<NodoPersona> l) {
    	this.numVertices = numV;
    	this.personas = l;
    }
  /**
  * PRE: nomP está en el grafo
  * @param nomP Nombre de la persona fallecida
  * @param distMax Distancia máxima a la que se reparte la herencia
  * @return Lista de personas que no reciben herencia
  */
 public ArrayList<String> parientesEnfadados(String nomP, int distMax){
	 NodoPersona inicio = null;
	 for(NodoPersona muerto: personas) {
		 if(muerto.nombre.equals(nomP)) inicio = muerto;
	 }
//	 ArrayList<String> copia = new ArrayList<String>();
//	 for(NodoPersona nodo: personas) {
//		 copia.add(nodo.nombre);
//	 }
	 ArrayList<String> visitados = inicio.parientesEnfadados(nomP, distMax);
//	 for(String vis: visitados) {
//		 copia.remove(vis);
//	 }
	 return visitados;
 }
}
