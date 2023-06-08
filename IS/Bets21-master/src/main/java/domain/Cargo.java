package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Entity
@IdClass(CargoId.class)
public class Cargo {
	@Id
	@ManyToOne
	private Usuario user;
	@Id
	@ManyToOne
	private Sesion sesion;
	
	public Cargo() {
//		this.user = user;
//		this.sesion = sesion;
	}

	public void setId(CargoId cargoId) {
        this.user = cargoId.getUser();
        this.sesion = cargoId.getSesion();
	}

	public Usuario getUser() {
		return this.user;
	}

	public Sesion getSesion() {
		return this.sesion;
	}
}
