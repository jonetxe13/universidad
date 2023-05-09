package businessLogic;
import java.util.ArrayList;
//hola
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Vector;

import javax.jws.WebMethod;
import javax.jws.WebService;

import configuration.ConfigXML;
import dataAccess.DataAccess;
import domain.Question;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;
import domain.Actividad;
import domain.Encargado;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

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
	public boolean userExists(String correo, String contrasenna) {
		dbManager.open(false);
		Usuario newUser = new Usuario(correo, contrasenna);
		boolean existe = dbManager.userExists(newUser);
		return existe;
	}
	@WebMethod
	public boolean encargadoExists(String correo, String contrasenna) {
		dbManager.open(false);
		Encargado encargado = new Encargado(correo, contrasenna);
		boolean existe = dbManager.encargadoExists(encargado);
		return existe;
	}
	@WebMethod
	public Encargado getEncargado(String correo, String contrasenna) {
		dbManager.open(false);
		Encargado encargado = new Encargado(correo, contrasenna);
		Encargado enc = dbManager.getEncargado(encargado);
		return enc;
	}

	@WebMethod
	public Sala getSala(String string) {
		dbManager.open(false);
		Sala sala1 = dbManager.getSala(string);
		return sala1;
	}
	@WebMethod
	public List<Sesion> sesionesSemana(){
		dbManager.open(false);
		List<Sesion> lista = dbManager.getSesionesSemana();
		return lista;
	}

	@WebMethod
	public boolean addReserva(Sesion seleccionado, Usuario user) {
		dbManager.open(false);
		boolean bien = dbManager.addReserva(seleccionado, user);
		return bien;
	}

	@WebMethod
	public Sesion addAListaEspera(Sesion sesion, Usuario user) {
		dbManager.open(false);
		Sesion añadidoOno = dbManager.addAListaEspera(sesion, user);
		return añadidoOno;
	}
	
	@WebMethod
	public Sesion getSesion(String fecha, int salaNum) {
		dbManager.open(false);
		Sesion sesion = dbManager.getSesion(fecha, salaNum);
		return sesion;
	}

	@WebMethod
	public boolean cancelarReserva(Sesion sesion, Usuario user) {
		dbManager.open(false);
		boolean res = dbManager.cancelarReserva(sesion, user);
		return res;
	}

	@WebMethod
	public Sesion annadirSesion(String text, String salaNum, String text3, String text4, String text5) {
		dbManager.open(false);
		int number = 0;
		Sesion sesionAnnadida = null;
		try {
		    number = Integer.parseInt(salaNum);
		} catch (NumberFormatException ex) {
		    // El texto no es un número entero válido
			System.out.println("el numero que has introducido no es un entero o no es un numero valido");
		}
	    try {
			sesionAnnadida = dbManager.annadirSesion(text, number, text3, text4, text5);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    System.out.println("se ha annadido");
		return sesionAnnadida;
	}

	@WebMethod
	public Sesion quitarSesion(String fecha, String salaNum) {
		dbManager.open(false);
		int number = 0;
		try {
		    number = Integer.parseInt(salaNum);
		} catch (NumberFormatException ex) {
		    // El texto no es un número entero válido
			System.out.println("el numero que has introducido no es un entero o no es un numero valido");
		}
		Sesion ses = dbManager.quitarSesion(fecha, number);
		
		return ses;
	}

	@WebMethod
	public List<Actividad> getActividades() {
		dbManager.open(false);
		List<Actividad> lista = dbManager.getActividades();
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
		return null;
	}
}

