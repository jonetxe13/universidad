
public class Main {
	public static void main(String[] args) {
		Nodo nodo16 = new Nodo(16,null,null);
		Nodo nodo27 = new Nodo(27,null,null);
		Nodo nodo35 = new Nodo(35,nodo16,nodo27);
		Nodo nodo10 = new Nodo(10,null,null);
		Nodo nodo24 = new Nodo(24,null,nodo10);
		Nodo root = new Nodo(40,nodo35,nodo24);
		
		ArbolBinario arbolEquilibrado = new ArbolBinario(root);
		
		arbolEquilibrado.anadirHijosAHojas();
		System.out.println(arbolEquilibrado.recorrerArbol());
		
		Nodo nodo62 = new Nodo(6,null,null);
		Nodo nodo4 = new Nodo(4,null,null);
		Nodo nodo8 = new Nodo(8,null,null);
		Nodo nodo5 = new Nodo(5,nodo62,null);
		Nodo nodo61 = new Nodo(6,nodo4,nodo8);
		Nodo root2 = new Nodo(7,nodo5,nodo61);
		
		ArbolBinario arbolRandom = new ArbolBinario(root2);
		System.out.println(arbolRandom.listaDeNivel(2));
	}
}