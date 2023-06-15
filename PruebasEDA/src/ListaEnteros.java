//import java.util.ArrayList;
//
//public class ListaEnteros {
//	NodoEnteros first;
//	NodoEnteros last;
//	public NodoEnteros getFirst() {
//		return first;
//	}
//	public void setFirst(NodoEnteros first) {
//		this.first = first;
//	}
//	public NodoEnteros getLast() {
//		return last;
//	}
//	public void setLast(NodoEnteros last) {
//		this.last = last;
//	}
//
//	public ListaEnteros(NodoEnteros first, NodoEnteros last) {
//		this.first=first;
//		this.last=last;
//	}
//	
//	public void eliminarNegativosConPositivo() {
//		//elimina los numeros negativos que tengan su version positiva dentro de la lista
//		NodoEnteros actualFirst = first;
//		NodoEnteros actualLast = last;
//		while(actualFirst.info < 0 && actualLast.info > 0 && actualFirst != null && actualLast !=null) {
//			if(actualFirst.info*(-1) < actualLast.info) {
//				actualLast = actualLast.prev;
//			}
//			else if(actualFirst.info*(-1) > actualLast.info) {
//				actualFirst = actualFirst.next;
//			}
//			else {
//				if(actualLast == last) {
//					actualLast.prev.next = actualLast.next;
//				}
//				else {
//					actualLast.prev.next = actualLast.next;
//					actualLast.next.prev = actualLast.prev;
//				}
//				actualLast = actualLast.prev;
//				actualFirst = actualFirst.next;
//			}
//		}
//	}
//
//}
