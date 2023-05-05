package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;

@Entity
public class Sesion {
	private String fecha;
	private int plazasDisponibles;
	private List<Actividad> listaActividades;
	private Sala sala;


	public Sesion(String dia, int plazas, Sala sala) {
		this.fecha = dia;
		this.plazasDisponibles = plazas;
		this.sala = sala;
	}
	
	public String getFecha() { return fecha;	}
	public void setFecha(String fecha) {	this.fecha = fecha; }	
	public int getPlazasDisponibles() {	return plazasDisponibles;	}
	public void setPlazasDisponibles(int plazasDisponibles) {	this.plazasDisponibles = plazasDisponibles;	}
	public List<Actividad> getListaActividades() {	return listaActividades;	}
	public void setListaActividades(List<Actividad> listaActividades) {	this.listaActividades = listaActividades;	}
	public Sala getSala() {	return sala; }
	public void setSala(Sala sala) { this.sala = sala;	}
	
}
