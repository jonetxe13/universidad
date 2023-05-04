package domain;

import javax.persistence.Entity;

@Entity
public class Usuario {
	private String correo;

	private String contrasenna;
	

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
	public String toString() {
		System.out.println("El usuario es: " + this.correo + " y la contrasenna es: " + this.contrasenna);
		return "se ha imprimido bien";
	}
}
