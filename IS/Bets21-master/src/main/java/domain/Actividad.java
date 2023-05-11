package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Actividad {
	@Id
	private String nombre;

	private int gradoExigencia;
	private int precio;

	public Actividad(String n, int g, int p) {
		this.nombre = n;
		this.gradoExigencia = g;
		this.precio = p;
	}

	public String getNombre() {	return nombre; }
	public void setNombre(String nombre) {	this.nombre = nombre;	}
	public int getGradoExigencia() {	return gradoExigencia;	}
	public void setGradoExigencia(int gradoExigencia) {	this.gradoExigencia = gradoExigencia; }
	public int getPrecio() {	return precio;	}
	public void setPrecio(int precio) {	this.precio = precio;	}

}
