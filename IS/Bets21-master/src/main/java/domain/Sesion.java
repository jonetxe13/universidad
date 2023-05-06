package domain;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sesion {
	@Id
	private String fecha;
	private int plazasDisponibles;
	private List<Actividad> listaActividades;
	private Queue<Usuario> listaEspera;
	private Sala sala;


	public Sesion(String dia, int plazas, Sala sala) {
		this.fecha = dia;
		this.plazasDisponibles = plazas;
		this.sala = sala;
		this.listaEspera = new LinkedList<Usuario>();
	}
	
	public String getFecha() { return fecha;	}
	public void setFecha(String fecha) {	this.fecha = fecha; }	
	public int getPlazasDisponibles() {	return plazasDisponibles;	}
	public void setPlazasDisponibles(int plazasDisponibles) {	this.plazasDisponibles = plazasDisponibles;	}
	public List<Actividad> getListaActividades() {	return listaActividades;	}
	public void setListaActividades(List<Actividad> listaActividades) {	this.listaActividades = listaActividades;	}
	public Sala getSala() {	return sala; }
	public void setSala(Sala sala) { this.sala = sala;	}
	public Queue<Usuario> getListaEspera() { return listaEspera; }
	public void setListaEspera(Queue<Usuario> listaEspera) { this.listaEspera = listaEspera; }

	public String crearHash(Usuario user) {
		String codigo = this.fecha+"-"+user.getCorreo();
		System.out.print(codigo);
		return codigo;
	}
	public void addAListaEspera(Usuario user) {
		listaEspera.add(user);
		for(Usuario usr: this.getListaEspera()) {
			System.out.print(usr.getCorreo());			
		}
	}
}
