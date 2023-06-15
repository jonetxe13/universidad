import java.util.HashMap;
import java.util.LinkedList;

public class Nodo {
	int info;
	Nodo left;
	Nodo right;
	
	public Nodo(int info, Nodo left, Nodo right) {
		this.info = info;
		this.left=left;
		this.right=right;
	}
	public boolean hasLeft() {
		return left != null;
	}
	public boolean hasRight() {
		return right != null;
	}

	public boolean isLeaf() {
		return left == null && right == null;
	}
		
	public LinkedList<Integer> caminoMasPesadoBusqueda() {
		LinkedList<Integer> lista = new LinkedList<Integer>();
		Nodo actual = this;
		while(!actual.isLeaf()) {
			lista.add(actual.info);
			actual = actual.right;
		}
		lista.add(actual.info);
		return lista;
	}
	public Nodo anadirHijosAHojas(int numPares, int numImpares) {
		if(this.hasLeft()) {
			if(this.left.info % 2 == 0) {
				this.left.anadirHijosAHojas(numPares+1, numImpares);
			}
			else{
				this.left.anadirHijosAHojas(numPares, numImpares+1);
			}
		}
		if(this.hasRight()) {
			if(this.right.info % 2 == 0) {
				this.right.anadirHijosAHojas(numPares+1, numImpares);
			}
			else {
				this.right.anadirHijosAHojas(numPares, numImpares+1);
			}
		}
		if(this.isLeaf()) {
			this.right = new Nodo(numPares, null, null);
			this.left = new Nodo(numImpares, null, null);
		}
		return this.left;
	}
	public LinkedList<Integer> listaDeNivel(int i) {
		LinkedList<Integer> lista = new LinkedList<Integer>();
		if(this.hasLeft()) {
			lista.addAll(lista.size(), this.left.listaDeNivel(i-1)) ;
		}
		if(this.hasRight()) {
			lista.addAll(lista.size(), this.right.listaDeNivel(i-1)) ;
		}
		if(i == 0) {
			lista.add(this.info);
		}
		return lista;
	}
	public LinkedList<Integer> caminoMasPesado(){
		if(this.isLeaf()) {
			LinkedList<Integer> lista = new LinkedList<Integer>();
			lista.add(this.info);
			return lista;
		}
		LinkedList<Integer> listaIzq = new LinkedList<Integer>();
		LinkedList<Integer> listaDer = new LinkedList<Integer>();
		
		if(this.hasLeft()) {
			listaIzq = this.left.caminoMasPesado();
			listaIzq.addFirst(this.info);
		}
		if(this.hasRight()) {
			listaDer = this.right.caminoMasPesado();
			listaDer.addFirst(this.info);
		}
		if(this.sumaLinkedList(listaIzq) < this.sumaLinkedList(listaDer)) {
			listaIzq = listaDer;
		}
		return listaIzq;
	}
	public int sumaLinkedList(LinkedList<Integer> lista) {
		int res = 0;
		for(Integer i: lista) {
			res+=i;
		}
		return res;
	}
	public ResultadoPonderado esEquiponderado() {
		ResultadoPonderado izq = new ResultadoPonderado(true, 0);
		ResultadoPonderado der = new ResultadoPonderado(true, 0);
		if(this.hasLeft()) {
			izq = this.left.esEquiponderado();
			
		}
		if(this.hasRight()) {
			der = this.right.esEquiponderado();
			
		}
		if(izq.suma != der.suma) {
			return new ResultadoPonderado(false,-1);
		}
		else {
			return new ResultadoPonderado(true,izq.suma+der.suma+this.info);
		}
		
	}
	public LinkedList<Integer> listaNivelDeMayores(int niv, int num) {
		LinkedList<Integer> listaizq = new LinkedList<Integer>();
		LinkedList<Integer> listader = new LinkedList<Integer>();
		
		if(this.hasLeft()) {
			listaizq = this.left.listaNivelDeMayores(niv-1, num);
		}
		if(this.hasRight()) {
			listader = this.right.listaNivelDeMayores(niv-1, num);
		}
		if(niv == 0 && this.info>num) {
			listaizq.add(this.info);
			return listaizq;
		}
		listaizq.addAll(listader);	
		return listaizq;
	}
}
