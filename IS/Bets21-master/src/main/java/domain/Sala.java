package domain;

import java.util.List;

import javax.persistence.Entity;

@Entity
public class Sala {
	private String nombre;
	private int aforoMax;
	private List<Sesion> listaSesiones;
	


	public Sala(String nombre, int aforo, List<Sesion> lista) {
		this.nombre = nombre;
		this.aforoMax = aforo;
		this.listaSesiones = lista;
	}

	public int getAforoMax() {	return aforoMax; }
	public void setAforoMax(int aforoMax) {	this.aforoMax = aforoMax;	}
	public String getNombre() {	return nombre;	}
	public void setNombre(String nombre) {	this.nombre = nombre;	}
	public List<Sesion> getListaSesiones() { return listaSesiones; }
	public void setListaSesiones(List<Sesion> listaSesiones) { this.listaSesiones = listaSesiones; }
	
	public String toString() {
		String res;
		res = this.nombre + "; aforomax: " + this.aforoMax + " {";
		for(Sesion ses: listaSesiones) {
			res += ses.getFecha();
		}
		res += " }";
		return res;
	}
}
