package domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sesion {
	@Id
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

	public String crearHash(Usuario user) {
		String codigo = generateCodigo(user);
		System.out.print(codigo);
		return codigo;
	}

	private String generateCodigo(Usuario user) {
		String codigo = this.fecha+"-"+user.getCorreo();
		return codigo;
	}
	
}
