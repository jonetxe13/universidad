package domain;

import javax.persistence.Entity;

@Entity
public class Actividad {
	private String nombre;

	private int gradoExigencia;
	private float precio;
	
	public Actividad(String n, int g, float p) {
		this.nombre = n;
		this.gradoExigencia = g;
		this.precio = p;
	}
	
	public String getNombre() {	return nombre; }
	public void setNombre(String nombre) {	this.nombre = nombre;	}
	public int getGradoExigencia() {	return gradoExigencia;	}
	public void setGradoExigencia(int gradoExigencia) {	this.gradoExigencia = gradoExigencia; }
	public float getPrecio() {	return precio;	}
	public void setPrecio(float precio) {	this.precio = precio;	}

}
