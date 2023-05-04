package domain;

import java.util.Date;

import javax.persistence.Entity;

@Entity
public class Sesion {
	private Date fecha;
	private int plazasDisponibles;

	public Sesion(Date fecha, int plazas) {
		this.fecha = fecha;
		this.plazasDisponibles = plazas;
	}
	
	public Date getFecha() { return fecha;	}
	public void setFecha(Date fecha) {	this.fecha = fecha; }	
	public int getPlazasDisponibles() {	return plazasDisponibles;	}
	public void setPlazasDisponibles(int plazasDisponibles) {	this.plazasDisponibles = plazasDisponibles;	}

}
