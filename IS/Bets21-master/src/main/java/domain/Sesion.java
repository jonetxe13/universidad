package domain;

import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class Sesion {
	@Id
	private String fecha;
	private int precio;
	private int plazasDisponibles;
	@ManyToMany
	private List<Actividad> listaActividades;
	@ManyToMany
	private Queue<Usuario> listaEspera;
	@Id
	@ManyToOne
	private Sala sala;


	public Sesion(String fecha, Sala sala, int plazas, List<Actividad> listaActividades, int precio ) {
		this.fecha = fecha;
		if(plazas > sala.getAforoMax())
			System.out.println("la sesion no puede tener mas plazas que la sala");
		this.plazasDisponibles = plazas;
		this.sala = sala;
		this.listaEspera = new LinkedList<>();
		this.listaActividades = listaActividades;
	}

	public String getFecha() { return fecha; }
	public void setFecha(String fecha) { this.fecha = fecha; }
	public int getPrecio() { return precio; }
	public void setPrecio(int precio) { this.precio = precio; }
	public int getPlazasDisponibles() {	return plazasDisponibles; }
	public void setPlazasDisponibles(int plazasDisponibles) {	this.plazasDisponibles = plazasDisponibles;	}
	public List<Actividad> getListaActividades() {	return listaActividades;	}
	public void setListaActividades(List<Actividad> listaActividades) {	this.listaActividades = listaActividades;	}
	public Sala getSala() {	return sala; }
	public void setSala(Sala sala) { this.sala = sala;	}
	public Queue<Usuario> getListaEspera() { return listaEspera; }
	public void setListaEspera(Queue<Usuario> listaEspera) { this.listaEspera = listaEspera; }

	public String crearHash(Usuario user) {
		String codigo = this.fecha+"/" + this.sala.getNumero()+"/"+user.getCorreo();
		System.out.print(codigo);
		return codigo;
	}
	public void addAListaEspera(Usuario user) {
		System.out.println("se te ha añadido a la lista de espera");
		listaEspera.add(user);
		for(Usuario usr: this.getListaEspera()) {
			System.out.print(usr.getCorreo());
		}
	}
	public Usuario removeDeListaEspera() {
		Usuario user = listaEspera.poll();
		if(user != null) {
			System.out.println("se te ha quitado de la lista de espera y se te ha annadido la reserva");
		}
		return user;
	}
}
