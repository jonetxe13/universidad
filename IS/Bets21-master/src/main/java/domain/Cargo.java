package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;

@Entity
@IdClass(CargoId.class)
public class Cargo {
	@Id
	private Usuario user;
	@Id
	private Sesion sesion;
	
	public Cargo() {
//		this.user = user;
//		this.sesion = sesion;
	}

	public void setId(CargoId cargoId) {
        this.user = cargoId.getUser();
        this.sesion = cargoId.getSesion();
	}
}
