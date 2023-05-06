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
	private List<String> listaReservas;

	public Usuario(String correo, String contrasenna) {
		this.correo = correo;
		this.contrasenna = contrasenna;
		listaReservas = new ArrayList<String>();
	}
	
	public String getCorreo() {	return correo; }
	public void setCorreo(String correo) { this.correo = correo; }
	public String getContrasenna() { return contrasenna; }
	public void setContrasenna(String contrasenna) { this.contrasenna = contrasenna; }
	public List<String> getListaReservas() { return listaReservas; }
	public void setListaReservas(List<String> listaReservas) { this.listaReservas = listaReservas; }	
	
//	public String toString() {
//		String res = "El usuario es: " + this.correo + " y la contrasenna es: " + this.contrasenna;
//		System.out.println(res);
//		return res;
//	}
	public void addReserva(String codigo) {
		if(!listaReservas.contains(codigo)) {
			listaReservas.add(codigo);		
			System.out.println("se te ha anadido a la lista de espera");
		}
		else {
			System.out.println("ya estas en la lista de espera de esta sesion");
		}
	}
}
