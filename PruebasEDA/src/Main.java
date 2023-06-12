import java.util.ArrayList;
import java.util.LinkedList;

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
		
		
		NodoCalle nodoH = new NodoCalle("H");
		NodoCalle nodoI = new NodoCalle("I");
		LinkedList<NodoCalle> listaF = new LinkedList<NodoCalle>();
		listaF.add(nodoH);
		listaF.add(nodoI);
		NodoCalle nodoJ = new NodoCalle("J");
		NodoCalle nodoF = new NodoCalle("F");
		nodoF.enlaces = listaF;
		NodoCalle nodoG = new NodoCalle("G");
		NodoCalle nodoE = new NodoCalle("E");
		NodoCalle nodoD = new NodoCalle("D");
		NodoCalle nodoB = new NodoCalle("B");
		NodoCalle nodoC = new NodoCalle("C");
		NodoCalle nodoA = new NodoCalle("A");
		nodoJ.enlaces.add(nodoC);
		nodoC.enlaces.add(nodoG);
		nodoG.enlaces.add(nodoJ);
		nodoA.enlaces.add(nodoB);
		nodoA.enlaces.add(nodoC);
		nodoB.enlaces.add(nodoD);
		nodoD.enlaces.add(nodoE);
		nodoE.enlaces.add(nodoF);
		nodoE.enlaces.add(nodoG);
		ArrayList<NodoCalle> calles = new ArrayList<NodoCalle>();
		calles.add(nodoA);
		calles.add(nodoB);
		calles.add(nodoC);
		calles.add(nodoD);
		calles.add(nodoE);
		calles.add(nodoF);
		calles.add(nodoG);
		calles.add(nodoH);
		calles.add(nodoI);
		Callejero grafo = new Callejero(10,calles);
		System.out.println(grafo.hayCamino(nodoA, nodoI));
		System.out.println(grafo.hayCamino2(nodoA.nomCalle, nodoI.nomCalle));
	}
}