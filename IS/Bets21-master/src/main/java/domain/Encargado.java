package domain;

import javax.jws.WebMethod;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Encargado extends Usuario{
//	@Id
//	private String correo;
    private static Encargado instance;
    
	public Encargado(String correo, String contrasenna) {
		super(contrasenna, contrasenna);
	}
	
    public static Encargado getInstance(String correo, String contrasenna) {
        if (instance == null) {
            instance = new Encargado(correo, contrasenna);
        }
        return instance;
    }
	@WebMethod
	public boolean isEncargado() {
		return true;
	}
}
