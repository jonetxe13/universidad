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
	public ResultadoCamino caminoMasPesado() {
		if(this.isLeaf()) {
			return new ResultadoCamino(this.info, this.info);
		}
		ResultadoCamino R = new ResultadoCamino(0,0);
		ResultadoCamino L = new ResultadoCamino(0,0);
		if(this.hasLeft()) {
			L = this.left.caminoMasPesado();
		}
		if(this.hasRight()) {
			R = this.right.caminoMasPesado();
		}
		if(R.peso > L.peso) {
			L = R;
		}
		L.peso += this.info;
		L.lista.addFirst(this.info) ;
		System.out.println(L.lista);
		return L;
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
}
