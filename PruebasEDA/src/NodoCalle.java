import java.util.LinkedList;

public class NodoCalle {
	String nomCalle;
	LinkedList<NodoCalle> enlaces;
	
	public NodoCalle(String nomCalle) {
		this.nomCalle=nomCalle;
		this.enlaces=new LinkedList<NodoCalle>();
	}
}
