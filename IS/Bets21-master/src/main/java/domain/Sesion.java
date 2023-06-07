package domain;

import java.util.Date;
//import java.io.Serializable;
import java.util.LinkedList;
import java.util.Queue;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
//@IdClass(SesionId.class)
public class Sesion {
	@Id
	private Date fecha;
	private int plazasDisponibles;
	private int precio;
	@ManyToOne
	private Actividad actividad;
	@ManyToMany
	private Queue<Usuario> listaEspera;
	@Id
	@ManyToOne
	private Sala sala;

	public Sesion() {}
	public Sesion(Date fecha, Sala sala, int plazas, Actividad actividad, int precio) {
		this.fecha = fecha;
		if(plazas > sala.getAforoMax())
			System.out.println("la sesion no puede tener mas plazas que la sala");
		this.plazasDisponibles = plazas;
		this.sala = sala;
		this.listaEspera = new LinkedList<>();
		this.actividad = actividad;
		this.precio = actividad.getPrecio();
	}

	public Date getFecha() { return fecha; }
	public void setFecha(Date fecha) { this.fecha = fecha; }
	public int getPrecio() { return precio; }
	public void setPrecio(int precio) { this.precio = precio; }
	public int getPlazasDisponibles() {	return plazasDisponibles; }
	public void setPlazasDisponibles(int plazasDisponibles) {	this.plazasDisponibles = plazasDisponibles;	}
	public Actividad getActividad() { return actividad;	}
	public void setActividad(Actividad actividad) {	this.actividad = actividad;	}
	public Sala getSala() {	return sala; }
	public void setSala(Sala sala) { this.sala = sala;	}
	public Queue<Usuario> getListaEspera() { return listaEspera; }
	public void setListaEspera(Queue<Usuario> listaEspera) { this.listaEspera = listaEspera; }

	public String crearHash(Usuario user) {
		String codigo = this.fecha+"/" + this.sala.getNumero()+"/"+user.getCorreo();
//		System.out.print(codigo);
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
