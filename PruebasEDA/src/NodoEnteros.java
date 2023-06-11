
public class NodoEnteros {
	int info;
	NodoEnteros prev;
	NodoEnteros next; 
	public int getInfo() {
		return info;
	}
	public void setInfo(int info) {
		this.info = info;
	}
	public NodoEnteros getPrev() {
		return prev;
	}
	public void setPrev(NodoEnteros prev) {
		this.prev = prev;
	}
	public NodoEnteros getNext() {
		return next;
	}
	public void setNext(NodoEnteros next) {
		this.next = next;
	}
	public NodoEnteros(int info, NodoEnteros prev, NodoEnteros next) {
		this.info=info;
		this.prev=prev;
		this.next=next;
	}
}