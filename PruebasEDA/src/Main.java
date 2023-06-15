import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

public class Main {
	public static void main(String[] args) {
//		NodoTurista guia3 = new NodoTurista("guia3");
//		NodoTurista guia2 = new NodoTurista("guia2");
//		NodoTurista guia1 = new NodoTurista("guia1");
//		guia1.nextGuia = guia2;
//		guia2.nextGuia = guia3;
//		guia3.nextGuia = null;
//		
//		NodoTurista nodo1 = new NodoTurista("A");
//		guia1.next = nodo1;
//		nodo1.next = guia2;
//		nodo1.prev = guia1;
//		NodoTurista nodo2 = new NodoTurista("B");
//		guia2.next = nodo2;
//		nodo2.next = guia3;
//		nodo2.prev = guia2;
//		NodoTurista nodo3 = new NodoTurista("D");
//		nodo3.prev = guia3;
//		guia3.next = nodo3;
//		
//		ListaTuristas listaTuristas = new ListaTuristas();
//		listaTuristas.first = guia1;
//		listaTuristas.last = nodo3;
//		
//		listaTuristas.anadirTuristaAGrupo("C", "guia2");
		
		Nodo nodo11 = new Nodo(1,null,null);
		Nodo nodo12 = new Nodo(1,null,null);
		Nodo nodo3 = new Nodo(3,nodo11,nodo12);
		Nodo nodo5 = new Nodo(5,null,null);
		Nodo nodo4 = new Nodo(4,nodo5,nodo3);
		Nodo nodo14 = new Nodo(14,null,null);
		Nodo root1 = new Nodo(7,nodo4,nodo14);
		
		ArbolBinario arbolEquiponderado = new ArbolBinario(root1);
		
		System.out.println(arbolEquiponderado.esEquiponderado());
		
		Nodo nodo9 = new Nodo(9,null,null);
		Nodo nodo32 = new Nodo(32,null,null);
		Nodo nodo60 = new Nodo(60,null,null);
		Nodo nodo15 = new Nodo(15,null,null);
		Nodo nodo84 = new Nodo(84,null,null);
		Nodo nodo8 = new Nodo(8,null,nodo9);
		Nodo nodo50 = new Nodo(50,nodo32,nodo60);
		Nodo nodo13 = new Nodo(13,nodo8,nodo15);
		Nodo nodo72 = new Nodo(72,nodo50,nodo84);
		Nodo root = new Nodo(25, nodo13, nodo72);
		
		ArbolBinario arbolBusqueda = new ArbolBinario(root);
		System.out.println(arbolBusqueda.listaNivelDeMayores(2, 14));
		
//		Nodo nodo62 = new Nodo(6,null,null);
//		Nodo nodo4 = new Nodo(4,null,null);
//		Nodo nodo8 = new Nodo(8,null,null);
//		Nodo nodo5 = new Nodo(5,nodo62,null);
//		Nodo nodo61 = new Nodo(6,nodo4,nodo8);
//		Nodo root2 = new Nodo(7,nodo5,nodo61);
//		
//		ArbolBinario arbolRandom = new ArbolBinario(root2);
//		System.out.println(arbolRandom.listaDeNivel(2));
//		
		
//		NodoCalle nodoH = new NodoCalle("H");
//		NodoCalle nodoI = new NodoCalle("I");
//		LinkedList<NodoCalle> listaF = new LinkedList<NodoCalle>();
//		listaF.add(nodoH);
//		listaF.add(nodoI);
//		NodoCalle nodoJ = new NodoCalle("J");
//		NodoCalle nodoF = new NodoCalle("F");
//		nodoF.enlaces = listaF;
//		NodoCalle nodoG = new NodoCalle("G");
//		NodoCalle nodoE = new NodoCalle("E");
//		NodoCalle nodoD = new NodoCalle("D");
//		NodoCalle nodoB = new NodoCalle("B");
//		NodoCalle nodoC = new NodoCalle("C");
//		NodoCalle nodoA = new NodoCalle("A");
//		nodoJ.enlaces.add(nodoC);
//		nodoC.enlaces.add(nodoG);
//		nodoG.enlaces.add(nodoJ);
//		nodoA.enlaces.add(nodoB);
//		nodoA.enlaces.add(nodoC);
//		nodoB.enlaces.add(nodoD);
//		nodoD.enlaces.add(nodoE);
//		nodoE.enlaces.add(nodoF);
//		nodoE.enlaces.add(nodoG);
//		ArrayList<NodoCalle> calles = new ArrayList<NodoCalle>();
//		calles.add(nodoA);
//		calles.add(nodoB);
//		calles.add(nodoC);
//		calles.add(nodoD);
//		calles.add(nodoE);
//		calles.add(nodoF);
//		calles.add(nodoG);
//		calles.add(nodoH);
//		calles.add(nodoI);
//		Callejero grafo = new Callejero(10,calles);
//		System.out.println(grafo.hayCamino(nodoA, nodoI));
//		System.out.println(grafo.hayCamino2(nodoA.nomCalle, nodoI.nomCalle));
//		System.out.println(grafo.calcularDistancia(nodoA, nodoJ));
//		System.out.println(grafo.obtenerCamino(nodoA, nodoJ));
//	}
		ArrayList<String> palabrasNota = new ArrayList<String>();
		ArrayList<String> palabrasRevista = new ArrayList<String>();
		palabrasNota.add("give");
		palabrasNota.add("me");
		palabrasNota.add("one");
		palabrasNota.add("million");
		palabrasNota.add("TONIGHT");
		
		palabrasRevista.add("give");
		palabrasRevista.add("me");
		palabrasRevista.add("one");
		palabrasRevista.add("million");
		palabrasRevista.add("reasons");
		palabrasRevista.add("to");
		palabrasRevista.add("trust");
		palabrasRevista.add("tonight");
		
		System.out.println(notaPosible(palabrasNota, palabrasRevista));
		
		
		NodoPersona joseba = new NodoPersona("joseba");
		NodoPersona luisa = new NodoPersona("luisa");
		NodoPersona aurora = new NodoPersona("aurora");
		NodoPersona jorge = new NodoPersona("jorge");
		NodoPersona xabi = new NodoPersona("xabi");
		NodoPersona santi = new NodoPersona("santi");
		NodoPersona amaia = new NodoPersona("amaia");
		ArrayList<NodoPersona> list = new ArrayList<NodoPersona>();
		list.add(joseba);
		list.add(luisa);
		list.add(aurora);
		list.add(jorge);
		list.add(xabi);
		list.add(santi);
		list.add(amaia);
		jorge.adyacentes.add(joseba);
		jorge.adyacentes.add(xabi);
		xabi.adyacentes.add(santi);
		xabi.adyacentes.add(amaia);
		joseba.adyacentes.add(luisa);
		joseba.adyacentes.add(aurora);
		Herencia grafoHerencia = new Herencia(7, list);
		System.out.println(grafoHerencia.parientesEnfadados(jorge.nombre, 1));
	}
	public static boolean notaPosible(ArrayList<String> palabrasNota, ArrayList<String> palabrasRevista) {
		HashMap<String, Integer> tabla = new HashMap<String, Integer>();
		for(String pal: palabrasRevista) {
			if(tabla.containsKey(pal.toLowerCase())) {
				tabla.put(pal.toLowerCase(), tabla.get(pal.toLowerCase())+1);
			}
			else {
				tabla.put(pal.toLowerCase(), 1);
			}
		}
		for(String pal2: palabrasNota) {
			if(tabla.containsKey(pal2.toLowerCase())) {
				if(tabla.get(pal2.toLowerCase()) == 0) return false;
				else {
					tabla.put(pal2.toLowerCase(), tabla.get(pal2.toLowerCase())-1);
				}
			}
			else {
				return false;
			}
		}
		return true;
	}
}