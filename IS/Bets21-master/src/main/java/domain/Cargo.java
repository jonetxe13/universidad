package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Cargo {
	@Id
	private Usuario user;
	@Id
	private Sesion sesion;
	
	public Cargo(Usuario user, Sesion sesion) {
		this.user = user;
		this.sesion = sesion;
	}
}
