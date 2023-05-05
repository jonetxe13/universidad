package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Usuario {
	@Id
	private String correo;
	
	private String contrasenna;
	private List<String> listaReservas = new ArrayList<String>();
	


	public Usuario(String correo, String contrasenna) {
		this.correo = correo;
		this.contrasenna = contrasenna;
	}
	
	public String getCorreo() {
		return correo;
	}
	
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getContrasenna() {
		return contrasenna;
	}
	
	public void setContrasenna(String contrasenna) {
		this.contrasenna = contrasenna;
	}
	public List<String> getListaReservas() {
		return listaReservas;
	}
	
	public void setListaReservas(List<String> listaReservas) {
		this.listaReservas = listaReservas;
	}
	public String toString() {
		System.out.println("El usuario es: " + this.correo + " y la contrasenna es: " + this.contrasenna);
		return "se ha imprimido bien";
	}
	public void addReserva(String codigo) {
		listaReservas.add(codigo);
	}
}
