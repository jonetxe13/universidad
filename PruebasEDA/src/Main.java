
public class Main {
	public static void main(String[] args) {
		System.out.println("hola mundo");
		Nodo nodo16 = new Nodo(16,null,null);
		Nodo nodo27 = new Nodo(27,null,null);
		Nodo nodo35 = new Nodo(35,nodo16,nodo27);
		Nodo nodo10 = new Nodo(10,null,null);
		Nodo nodo24 = new Nodo(24,null,nodo10);
		Nodo root = new Nodo(40,nodo35,nodo24);
		
		ArbolBinario arbol = new ArbolBinario(root);
		
		System.out.println(arbol.root.info);
		arbol.anadirHijosAHojas();
		System.out.println(arbol.recorrerArbol());
	}
}