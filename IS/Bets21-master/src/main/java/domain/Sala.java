package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Sala {
	@Id
	private int numero;
	private int aforoMax;
	@OneToMany
	private List<Sesion> listaSesiones;

	public Sala(int numero, int aforo) {
		this.numero = numero;
		this.aforoMax = aforo;
		this.listaSesiones = new ArrayList<>();
	}

	public int getAforoMax() {	return aforoMax; }
	public void setAforoMax(int aforoMax) {	this.aforoMax = aforoMax;	}
	public int getNumero() {	return numero;	}
	public void setNumero(int numero) {	this.numero = numero;	}
	public List<Sesion> getListaSesiones() { return listaSesiones; }
	public void setListaSesiones(List<Sesion> listaSesiones) { this.listaSesiones = listaSesiones; }

	@Override
	public String toString() {
		String res;
		res = this.numero + "; aforomax: " + this.aforoMax + " {";
		if(listaSesiones != null) {
			for(Sesion ses: listaSesiones) {
				res += ses.getFecha();
			}
		}
		res += " }";
		return res;
	}
	public void addAListaSesiones(Sesion ses) {
		this.listaSesiones.add(ses);
	}
}
