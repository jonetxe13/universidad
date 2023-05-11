package domain;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Encargado {
	@Id
	private String correo;

	private String contrasenna;
    private static Encargado instance;
    
    public Encargado() {}
	public Encargado(String correo, String contrasenna) {
		this.correo = correo;
		this.contrasenna = contrasenna;
	}

    public static Encargado getInstance(String correo, String contrasenna) {
        if (instance == null) {
            instance = new Encargado(correo, contrasenna);
        }
        return instance;
    }
	public String getCorreo() { return correo; }
	public void setCorreo(String correo) { this.correo = correo; }
	public String getContrasenna() { return contrasenna; }
	public void setContrasenna(String contrasenna) { this.contrasenna = contrasenna; }
}
