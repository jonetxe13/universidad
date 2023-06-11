import java.util.LinkedList;

public class ResultadoCamino {
	int peso;
	LinkedList<Integer> lista;
	public ResultadoCamino(int peso, int info){
		this.peso = peso;
		this.lista = new LinkedList<Integer>();
		this.lista.add(info);
	}
}
