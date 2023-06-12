package businessLogic;
import java.util.Date;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Actividad;
import domain.Cargo;
import domain.Encargado;
import domain.Factura;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;

/**
 * It implements the business logic as a web service.
 */
@WebService(endpointInterface = "businessLogic.BLFacade")
public class BLFacadeImplementation  implements BLFacade {
	DataAccess dbManager;

	public BLFacadeImplementation()  {
		System.out.println("Creating BLFacadeImplementation instance");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
		    dbManager=new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
		    dbManager.initializeDB();
	    } else
		     dbManager=new DataAccess();
		dbManager.close();
	}

    public BLFacadeImplementation(DataAccess da)  {

		System.out.println("Creating BLFacadeImplementation instance with DataAccess parameter");
		ConfigXML c=ConfigXML.getInstance();

		if (c.getDataBaseOpenMode().equals("initialize")) {
			da.open(true);
			da.initializeDB();
			da.close();

		}
		dbManager=da;
	}

	public void close() {
		DataAccess dB4oManager=new DataAccess(false);

		dB4oManager.close();

	}

	/**
	 * This method invokes the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod
	 public void initializeBD(){
    	dbManager.open(false);
		dbManager.initializeDB();
		dbManager.close();
	}

	@WebMethod
	public Usuario createUsuario(String correo, String contrasenna) {
	    //The minimum bed must be greater than 0
		dbManager.open(false);
		Usuario qry=null;

		qry=dbManager.createUsuario(correo, contrasenna);

		dbManager.close();

		return qry;
	}
	@WebMethod
	public Usuario getUsuario(String correo) {
		dbManager.open(false);
		Usuario user = dbManager.getUsuario(correo);
		return user;
	}
	@WebMethod
	public boolean userExists(String correo) {
		dbManager.open(false);
		boolean existe = dbManager.userExists(correo);
		dbManager.close();
		return existe;
	}
	@WebMethod
	public boolean encargadoExists(String correo, String contrasenna) {
		dbManager.open(false);
		Encargado encargado = new Encargado(correo, contrasenna);
		boolean existe = dbManager.encargadoExists(encargado);
		dbManager.close();
		return existe;
	}
	@WebMethod
	public Encargado getEncargado(String correo, String contrasenna) {
		dbManager.open(false);
		Encargado encargado = new Encargado(correo, contrasenna);
		Encargado enc = dbManager.getEncargado(encargado);
		dbManager.close();
		return enc;
	}

	@WebMethod
	public Sala getSala(String string) {
		dbManager.open(false);
		Sala sala1 = dbManager.getSala(string);
		dbManager.close();
		return sala1;
	}
	@WebMethod
	public List<Sesion> sesionesSemana(){
		dbManager.open(false);
		List<Sesion> lista = dbManager.getSesionesSemana();
		dbManager.close();
		return lista;
	}

	@WebMethod
	public boolean addReserva(Sesion seleccionado, Usuario user) {
		dbManager.open(false);
		boolean bien;
		if(seleccionado.getPlazasDisponibles() == 0) {
			this.addAListaEspera(seleccionado, user);
			bien = false;
		}
		else {
			bien = dbManager.addReserva(seleccionado, user);
		}
		user = dbManager.getUsuario(user.getCorreo());
		if(user.getListaReservas().size() >= 5) {
			dbManager.crearCargo(user, seleccionado.crearHash(user));
		}
		dbManager.close();
		return bien;
	}

	@WebMethod
	public Sesion addAListaEspera(Sesion sesion, Usuario user) {
		Sesion annadidoOno = dbManager.addAListaEspera(sesion, user);
		if(annadidoOno!=null) System.out.println("addAListaEspera funciona");
		return annadidoOno;
	}

	@SuppressWarnings("deprecation")
	@WebMethod
	public Sesion getSesion(Date fecha, int salaNum) {
		dbManager.open(false);
		fecha.setMinutes(0);
		fecha.setSeconds(0);
		Sesion sesion = dbManager.getSesion(fecha, salaNum);
		dbManager.close();
		return sesion;
	}

	@WebMethod
	public boolean cancelarReserva(Sesion sesion, Usuario user) {
		dbManager.open(false);
		boolean res = dbManager.cancelarReserva(sesion, user);
		dbManager.eliminarCargo(user, sesion);
		dbManager.close();
		return res;
	}

	@WebMethod
	public Sesion annadirSesion(Date fecha, String salaNum, String text3, String text4, String text5) {
		dbManager.open(false);
    	System.out.println("llega hasta aqui");
		int number = 0;
		Sesion sesionAnnadida = null;
		try {
		    number = Integer.parseInt(salaNum);
		} catch (NumberFormatException ex) {
			System.out.println("el numero que has introducido no es un entero o no es un numero valido");
		}
	    try {
			sesionAnnadida = dbManager.annadirSesion(fecha, number, text3, text4, text5);
		} catch (Exception e) {
			e.printStackTrace();
		}
	    System.out.println("se ha annadido");
		dbManager.close();
		return sesionAnnadida;
	}

	@WebMethod
	public Sesion quitarSesion(Date fecha, String salaNum) {
		dbManager.open(false);
		int number = 0;
		try {
		    number = Integer.parseInt(salaNum);
		} catch (NumberFormatException ex) {
		    // El texto no es un número entero válido
			System.out.println("el numero que has introducido no es un entero o no es un numero valido");
		}
		Sesion ses = dbManager.quitarSesion(fecha, number);
		dbManager.close();
		return ses;
	}

	@WebMethod
	public List<Actividad> getActividades() {
		dbManager.open(false);
		System.out.println("llega hasta aqui");
		List<Actividad> lista = dbManager.getActividades();
		dbManager.close();
		return lista;
	}

	@WebMethod
	public Actividad annadirActividad(String nombre, String nivel, String precio) {
		dbManager.open(false);
		int nvl = 0;
		int prc = 0;
		try {
		    nvl = Integer.parseInt(nivel);
		    prc = Integer.parseInt(precio);
		} catch (NumberFormatException ex) {
		    // El texto no es un número entero válido
			System.out.println("el numero que has introducido no es un entero o no es un numero valido");
		}
		Actividad act = dbManager.annadirActividad(nombre, nvl, prc);
		dbManager.close();
		return act;
	}
	@WebMethod
	public List<Usuario> getListaUserCargos(){
		dbManager.open(false);
		List<Usuario> lista = dbManager.getListaUserCargos();
		dbManager.close();
		return lista;
	}
	@WebMethod
	public List<Sesion> getListaSesionCargos(Usuario user) {
		dbManager.open(false);
		List<Sesion> lSesion = dbManager.getListaSesionCargos(user);
		dbManager.close();
		return lSesion;
	}

	@Override
	public List<Factura> getFacturas(Usuario usuario) {
		dbManager.open(false);
		Usuario user = dbManager.getUsuario(usuario.getCorreo());
		List<Factura> lSesion = dbManager.getFacturas(user);
		System.out.println(lSesion);
		dbManager.close();
		return lSesion;
	}
}

