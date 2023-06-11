import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Stack;

public class ArbolBinario {
	Nodo root;
	
	public ArbolBinario(Nodo root) {
		this.root = root;
	}
    public ArrayList<Integer> recorrerArbol() {
        ArrayList<Integer> valoresNodos = new ArrayList<>();
        recorrerEnProfundidad(root, valoresNodos);
        return valoresNodos;
    }

    private void recorrerEnProfundidad(Nodo nodo, ArrayList<Integer> valoresNodos) {
        if (nodo != null) {
            valoresNodos.add(nodo.info);
            recorrerEnProfundidad(nodo.left, valoresNodos);
            recorrerEnProfundidad(nodo.right, valoresNodos);
        }
    }
	public LinkedList<Integer> caminoMasPesado() {
		if(root != null) {
			return this.root.caminoMasPesado().lista;
		}
		else {
			return new LinkedList<Integer>();
		}
    }
	
	public LinkedList<Integer> caminoMasPesadoBusqueda() {
		if(root != null) {
			return this.root.caminoMasPesadoBusqueda();
		}
		else {
			return new LinkedList<Integer>();
		}
    }
	
	public void anadirHijosAHojas() {
		if(this.root != null) {
			if(this.root.info % 2 == 0) {
				this.root.anadirHijosAHojas(1,0);
			}
			else {
				this.root.anadirHijosAHojas(0,1);
			}
		}
	}
    private int sumarValores(LinkedList<Integer> lista) {
        int suma = 0;
        for (int valor : lista) {
            suma += valor;
        }
        return suma;
    }

    public boolean isEmpty() {
        return root == null;
    }
}
