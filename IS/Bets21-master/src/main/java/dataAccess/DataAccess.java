package dataAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.ResourceBundle;
import java.util.Vector;
import java.util.Iterator;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
//import javax.swing.text.html.HTMLDocument.Iterator;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Actividad;
import domain.Encargado;
import domain.Event;
import domain.Question;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;
import exceptions.QuestionAlreadyExist;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
		
	}

	public DataAccess()  {	
		 this(false);
	}
	
	
	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	public void initializeDB(){
		
		db.getTransaction().begin();
		try {

			
		   Calendar today = Calendar.getInstance();
		   
		   int month=today.get(Calendar.MONTH);
		   int year=today.get(Calendar.YEAR);
		   if (month==12) { month=0; year+=1;}  
	    
		   Actividad act1 = new Actividad("zumba", 3, 5);
		   Actividad act2 = new Actividad("pilates", 2, 15);
		   Actividad act3 = new Actividad("crossfit", 5, 25);
		   db.persist(act1);
		   db.persist(act2);
		   db.persist(act3);
		   List<Actividad> listaActividades = new ArrayList<Actividad>();
		   listaActividades.add(act1);
		   listaActividades.add(act2);		
		   listaActividades.add(act3);
		   
		   Sala sala1 = new Sala(1, 20);
		   Sala sala2 = new Sala(2, 20);
		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		   Calendar cal = Calendar.getInstance();
		   cal.add(Calendar.DATE, 1);
		   String fecha = sdf.format(cal.getTime());
		   
		   Encargado admin = Encargado.getInstance("admin@admin.com", "admin");
		   System.out.println("se ha annadido al encargado");
		   db.persist(admin);
		   
		   Usuario usuario = new Usuario("a@a.com", "nose");
		   System.out.println("se ha annadido el usuario");
		   db.persist(usuario);
		   
		   List<Sesion> lista = new ArrayList<Sesion>();
		   Sesion sesion1 = new Sesion(fecha, sala1, 1, listaActividades, 10 );
		   lista.add(sesion1);

		   cal.add(Calendar.DATE, 1);
		   fecha = sdf.format(cal.getTime());

		   Sesion ses2 = new Sesion(fecha, sala1, 10, listaActividades, 15 );
		   cal.add(Calendar.DATE, 1);
		   fecha = sdf.format(cal.getTime());
		   Sesion ses3 = new Sesion(fecha, sala1, 10, listaActividades, 15  );
		   cal.add(Calendar.DATE, 1);
		   fecha = sdf.format(cal.getTime());
		   Sesion ses4 = new Sesion(fecha, sala1, 10, listaActividades, 15  );
		   cal.add(Calendar.DATE, 1);
		   fecha = sdf.format(cal.getTime());
		   Sesion ses5 = new Sesion(fecha, sala1, 10, listaActividades, 15  );
		   cal.add(Calendar.DATE, 1);
		   fecha = sdf.format(cal.getTime());
		   Sesion ses6 = new Sesion(fecha, sala1, 10, listaActividades, 15  );
		   cal.add(Calendar.DATE, 1);
		   fecha = sdf.format(cal.getTime());
		   Sesion ses7 = new Sesion(fecha, sala1, 10, listaActividades, 15  );
		   Sesion ses8 = new Sesion(fecha, sala1, 10, listaActividades, 15  );
		   
		   db.persist(sesion1);
		   db.persist(ses2);
		   db.persist(ses3);
		   db.persist(ses4);
		   db.persist(ses5);
		   db.persist(ses6);
		   db.persist(ses7);
		   db.persist(ses8);
		   
		   lista.add(sesion1);	
		   lista.add(ses2);	
		   lista.add(ses3);	
		   lista.add(ses4);	
		   lista.add(ses5);	
		   lista.add(ses6);	
		   lista.add(ses7);	
		   lista.add(ses8);	
		   
		   sala1.setListaSesiones(lista);
			db.persist(sala1);
			db.persist(sala2);
			
			db.getTransaction().commit();
		}
		catch (Exception e){
			e.printStackTrace();
		}
		System.out.println("Db initialized");
	}

	public void open(boolean initializeMode){
		
		System.out.println("Opening DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		String fileName=c.getDbFilename();
		if (initializeMode) {
			fileName=fileName+";drop";
			System.out.println("Deleting the DataBase");
		}
		
		if (c.isDatabaseLocal()) {
			  emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
			  db = emf.createEntityManager();
		} else {
			Map<String, String> properties = new HashMap<String, String>();
			  properties.put("javax.persistence.jdbc.user", c.getUser());
			  properties.put("javax.persistence.jdbc.password", c.getPassword());
			  System.out.println(c);
			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	public Usuario createUsuario(String correo, String contrasenna){

		db.getTransaction().begin();
		Usuario user = db.find(Usuario.class, correo);
			if(user == null) {
				System.out.print("el usuario no existe asi que se crea");
				user = new Usuario(correo, contrasenna);
				db.persist(user);
				db.getTransaction().commit();
			}
			return user;
	}
	public boolean userExists(Usuario newUser) {
		System.out.println("Buscando el usuario en la base de datos");
		Usuario usuario = db.find(Usuario.class, newUser.getCorreo());
		if(usuario == null) {
			System.out.println("\n  no contiene a ese usuario");
			return false;
		}
		System.out.println("Si que contiene el usuario");
		return true;
	}
	public boolean encargadoExists(Encargado enc) {
		System.out.println("Buscando el encargado en la base de datos");
		db.getTransaction().begin();
		Encargado encargado = db.find(Encargado.class, enc.getCorreo());
		if(encargado == null) {
			System.out.println("\n  no contiene a ese encargado");
			db.getTransaction().commit();
			return false;
		}
		db.getTransaction().commit();
		System.out.println("Si que contiene el encargado");
		return true;
		
	}
	public Encargado getEncargado(Encargado encargado) {
		db.getTransaction().begin();
		Encargado enc = db.find(Encargado.class, encargado.getCorreo());
		if(enc == null) {
			System.out.println("el encargado no existe");
			db.getTransaction().commit();
			return null;
		}
		db.getTransaction().commit();
		return enc;
	}
	public Sesion getSesion(String fecha, int salaNum){
		db.getTransaction().begin();
		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha=:fecha", Sesion.class);
	    query.setParameter("fecha", fecha);
	    List<Sesion> sesiones = query.getResultList();
	    Sesion ses = null;
	    for(Sesion sesion2: sesiones) {
	    	if(sesion2.getSala().getNumero() == salaNum) {
	    		ses = sesion2;
	    	}
	    }
		if(ses == null) {
			System.out.print("la sesion no existe");
			return null;
		}
		db.persist(ses);
		db.getTransaction().commit();
		return ses;
	}

	public Sala getSala(String string) {
		System.out.println("Buscando la sala en la base de datos");
		db.getTransaction().begin();
		Sala res = db.find(Sala.class, string);
		if(res == null) return null;
		return res;
	}

	public List<Sesion> getSesionesSemana() {
		System.out.println("Buscando las sesiones de esta semana en la base de datos");
	    // Calculate the start and end of this week		   
	   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
	   Calendar cal = Calendar.getInstance();
	   cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	   String lunes = sdf.format(cal.getTime());
	   
	   System.out.println(lunes);
	   
	   //se le suma 6 dias para tener el domingo
	   cal.add(Calendar.DATE, 6);
	   String domingo = sdf.format(cal.getTime());
	   
	   System.out.println(domingo);

		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha BETWEEN :start AND :end ORDER BY s.fecha", Sesion.class);
		System.out.println(query);

	    query.setParameter("start", lunes);
	    query.setParameter("end", domingo);
		List<Sesion> resultado = query.getResultList();
		return resultado;
	}
	public List<Actividad> getActividades() {
		System.out.println("Buscando las actividades en la base de datos");
	   
		TypedQuery<Actividad> query = db.createQuery("SELECT a FROM Actividad a", Actividad.class);
		System.out.println(query);

		List<Actividad> resultado = query.getResultList();
		return resultado;
	}

	public boolean addReserva(Sesion sesion, Usuario user) {		  
		db.getTransaction().begin();
		
		String idUsuario = user.getCorreo();
		String idSesion = sesion.getFecha();
		
		Usuario usuario = db.find(Usuario.class, idUsuario);
		
		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha=:fecha", Sesion.class);
	    query.setParameter("fecha", idSesion);
	    List<Sesion> sesiones = query.getResultList();
	    Sesion ses = null;
	    for(Sesion sesion2: sesiones) {
	    	if(sesion2.getSala().getNumero() == sesion.getSala().getNumero()) {
	    		ses = sesion2;
	    	}
	    }
		if(ses.getPlazasDisponibles() == 0) {
			System.out.println("No se puede reservar porque esta llena");
			return false;
		}
		System.out.println(usuario.getListaReservas());
		if(usuario.getListaReservas() != null) {
			for(String r: user.getListaReservas()) {
				
				System.out.println(r.equals(ses.getFecha()+"-"+user.getCorreo()));

				if(r.equals(ses.getFecha()+"-"+user.getCorreo())){
					System.out.println("ya tienes esta sesion reservada");
					return false;
				}
			}	
		}
		String codigo = ses.crearHash(user);
		usuario.addReserva(codigo);
		ses.setPlazasDisponibles(ses.getPlazasDisponibles()-1);
		
		db.persist(usuario);
		db.persist(ses); 
		db.getTransaction().commit();
		return true;
	}

	public Sesion addAListaEspera(Sesion sesion, Usuario user) {
		db.getTransaction().begin();
		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha=:fecha", Sesion.class);
	    query.setParameter("fecha", sesion.getFecha());
	    List<Sesion> sesiones = query.getResultList();
	    Sesion ses = null;
	    for(Sesion sesion2: sesiones) {
	    	if(sesion2.getSala().getNumero() == sesion.getSala().getNumero()) {
	    		ses = sesion2;
	    	}
	    }
		Usuario usr = db.find(Usuario.class, user.getCorreo());
		if(ses.getListaEspera().contains(usr)) {
			System.out.println("ya estas en la lista de espera");
			return ses;
		}
		ses.addAListaEspera(usr);
		db.persist(ses);
		db.persist(usr);
		db.getTransaction().commit();
		return ses;	
	}

	public boolean cancelarReserva(Sesion sesion, Usuario user) {
		db.getTransaction().begin();
		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha=:fecha", Sesion.class);
	    query.setParameter("fecha", sesion.getFecha());
	    List<Sesion> sesiones = query.getResultList();
	    Sesion ses = null;
	    for(Sesion sesion2: sesiones) {
	    	if(sesion2.getSala().getNumero() == sesion.getSala().getNumero()) {
	    		ses = sesion2;
	    	}
	    }
		Usuario usr = db.find(Usuario.class, user.getCorreo());
		
		List<String> listaNomSesion = new ArrayList<String>();
		List<String> listaReservas = usr.getListaReservas();
		
		Iterator<String> iterator = listaReservas.iterator();
		while (iterator.hasNext()) {
		    String s = iterator.next();
		    String[] nomSesion = s.split("/");
		    listaNomSesion.add(nomSesion[0]);
		    if (nomSesion[0].equals(ses.getFecha()) && Integer.parseInt(nomSesion[1]) == ses.getSala().getNumero()) {
		        System.out.println("se ha encontrado la sesion con el mismo nombre que el de la reserva");
		        iterator.remove();
		        ses.setPlazasDisponibles(ses.getPlazasDisponibles() + 1);
	        	Usuario user2 = ses.removeDeListaEspera();
		        if(user2 != null) {
		        	System.out.println(user2);
		        	user2.addReserva(ses.crearHash(user2));
		        	ses.setPlazasDisponibles(ses.getPlazasDisponibles()-1);		        	
		        }
		    }
		}

		db.persist(usr);
		db.persist(ses);
		db.getTransaction().commit();
		return false;
	}

	public Sesion annadirSesion(String fecha, int sala, String listaActividades, String precio, String plazas) {
		db.getTransaction().begin();
		Sesion res = null;
		Sala salaSes = db.find(Sala.class, sala);
		TypedQuery<Sesion> listaSesionesEnEsaFecha = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha = :fecha", Sesion.class);
	    listaSesionesEnEsaFecha.setParameter("fecha", fecha);
		List<Sesion> listaSes = listaSesionesEnEsaFecha.getResultList();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = null;
		try {
			date = dateFormat.parse(fecha);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		int month = date.getMonth();
		Calendar fechaActual = Calendar.getInstance();
		int monthActual = fechaActual.get(Calendar.MONTH);
		
		if(listaSes != null) {
			for(Sesion ses: listaSes) {
				if(ses.getFecha() != null && ses.getFecha().equals(fecha) && ses.getSala() != null && ses.getSala().equals(salaSes)) {
					System.out.println("ya existe una sesion en esa sala en esa fecha");
//					return null;
				}
			}
//			System.out.println("algo pasa con el mes" + month + "  " + monthActual);
		}
		if(salaSes == null) {
			System.out.println("no se ha encontrado esa sala");
		}
		else if(month != monthActual) {
			// La fecha es de febrero
			System.out.println("el mes no es el mes en el que te encuentras");
			System.out.println(month + " " + monthActual);
		}
		else {
			String[] listaNomActiv = listaActividades.split(",");
			List<Actividad> listAct = new ArrayList<Actividad>();
			for(String nom: listaNomActiv) {
				Actividad activ = db.find(Actividad.class, nom);
				if(activ == null) {
					System.out.println("la actividad que has introducido no existe");
				}
				listAct.add(activ);
			}
			res = new Sesion(fecha, salaSes, Integer.parseInt(plazas), listAct, Integer.parseInt(precio));
			if(res != null) System.out.println("la sesion se ha creado bien");
			salaSes.addAListaSesiones(res);
		}
		db.persist(res);
		db.persist(salaSes);
		db.getTransaction().commit();
		return res;
	}

	public Sesion quitarSesion(String fecha, int salaNum) {
		db.getTransaction().begin();
		Sesion sesion = null;
		TypedQuery<Sesion> listaSesionesEnEsaFecha = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha = :fecha", Sesion.class);
	    listaSesionesEnEsaFecha.setParameter("fecha", fecha);
		List<Sesion> listSesion = listaSesionesEnEsaFecha.getResultList();
		for(Sesion ses: listSesion) {
			System.out.println(ses.getFecha());
			if(ses.getSala().getNumero() == salaNum) {
				sesion = ses;
				db.remove(ses);
			}
		}
		db.getTransaction().commit();
		return sesion;
	}

	public Actividad annadirActividad(String nombre, int nvl, int prc) {
		db.getTransaction().begin();
		Actividad act = db.find(Actividad.class, nombre);
		if(act != null) {
			System.out.println("la actividad ya existe");
		}
		else {
			act = new Actividad(nombre, nvl, prc);
			db.persist(act);
		}
		db.getTransaction().commit();
		return act;
	}
}
	