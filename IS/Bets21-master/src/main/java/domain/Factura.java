package domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Factura {
	private List<Cargo> listaCargos;
	@Id
	private String codigo;
	
	public Factura(Usuario user, List<Cargo> lista, int numero) {
		this.codigo = user.getCorreo() + "/" + numero;
		this.listaCargos = new ArrayList<Cargo>();
		setListaCargos(lista);
	}
	
	public List<Cargo> getListaCargos() {return listaCargos;}
	public void setListaCargos(List<Cargo> listaCargos) {this.listaCargos = listaCargos;}
	public String getCodigo() {return codigo;}
	public void setCodigo(String codigo) {this.codigo = codigo;}
	
	
}
