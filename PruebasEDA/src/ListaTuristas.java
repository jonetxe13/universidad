public class ListaTuristas {
	NodoTurista first;
    NodoTurista last;     
     
    //PRE: nomT (nombre del turista) no está en la lista
    //PRE: nomG (nombre del guía) sí está en la lista
    //PRE: no hay nombres repetidos
    public void anadirTuristaAGrupo(String nomT, String nomG) {
    	NodoTurista actual = first;
    	while(nomG != actual.nombre) {
    		actual = actual.nextGuia;
    	}
    	System.out.println(actual.nombre);
    	while(actual.next != last && actual.nextGuia != null) {
    		actual = actual.next; //este para ir al siguente guia y solo meterlo ahi
    	}
    	System.out.println(actual.nombre);
    	NodoTurista nuevo = new NodoTurista(nomT);
    	
    	nuevo.next = actual.next;
    	nuevo.prev = actual;
    	actual.next = nuevo;
    	nuevo.next.prev = nuevo;
    	
    	NodoTurista contador = first;
    	while(contador != last) {
    		System.out.println(contador.nombre + " ");
    		contador = contador.next;
    	}
    	System.out.println(last.nombre);
    }
}
