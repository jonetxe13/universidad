package dataAccess;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
//hello
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
//import javax.swing.text.html.HTMLDocument.Iterator;

import configuration.ConfigXML;
import domain.Actividad;
import domain.Encargado;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;

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


		   Actividad act1 = new Actividad("zumba", 3, 5);
		   Actividad act2 = new Actividad("pilates", 2, 15);
		   Actividad act3 = new Actividad("crossfit", 5, 25);

		   db.persist(act1);
		   db.persist(act2);
		   db.persist(act3);
		   
		   Sala sala1 = new Sala(1, 20);
		   Sala sala2 = new Sala(2, 20);
		   
		   Calendar cal = Calendar.getInstance();
		   cal.set(2023, Calendar.MAY, 12, 18, 0);
		   Date fecha = cal.getTime();

		   Encargado admin = Encargado.getInstance("admin@admin.com", "admin");
		   System.out.println("se ha annadido al encargado");
		   db.persist(admin);

		   Usuario usuario = new Usuario("a@a.com", "nose");
		   System.out.println("se ha annadido el usuario");
		   db.persist(usuario);

		   List<Sesion> lista = new ArrayList<>();
		   fecha.setMinutes(0);
		   fecha.setSeconds(0);
		   Sesion sesion1 = new Sesion(fecha, sala1, 0, act1, 10 );
		   lista.add(sesion1);

		   cal.set(2023, Calendar.MAY, 12, 20, 0);
		   fecha = cal.getTime();
		   fecha.setMinutes(0);
		   fecha.setSeconds(0);
		   Sesion ses2 = new Sesion(fecha, sala1, 10, act2, 15 );
		   
		   cal.set(2023, Calendar.MAY, 12, 22, 0);
		   fecha = cal.getTime();
		   fecha.setMinutes(0);
		   fecha.setSeconds(0);
		   Sesion ses3 = new Sesion(fecha, sala1, 10, act2, 15  );
		   Sesion ses4 = new Sesion(fecha, sala2, 10, act3, 15  );
		   cal.set(2023, Calendar.MAY, 13, 10, 0);
		   fecha = cal.getTime();
		   Sesion ses5 = new Sesion(fecha, sala1, 10, act3, 15  );
		   cal.set(2023, Calendar.MAY, 13, 13, 0);
		   fecha = cal.getTime();
		   Sesion ses6 = new Sesion(fecha, sala1, 10, act1, 15  );
		   cal.set(2023, Calendar.MAY, 13, 15, 0);
		   fecha = cal.getTime();
		   Sesion ses7 = new Sesion(fecha, sala1, 10, act2, 15  );
		   Sesion ses8 = new Sesion(fecha, sala2, 10, act1, 15  );
		   
		   lista.add(sesion1);
		   lista.add(ses2);
		   lista.add(ses3);
		   lista.add(ses5);
		   lista.add(ses6);
		   lista.add(ses7);

		   sala1.setListaSesiones(lista);
		   
		   db.persist(sesion1);
		   db.persist(ses2);
		   db.persist(ses3);
		   db.persist(ses4);
		   db.persist(ses5);
		   db.persist(ses6);
		   db.persist(ses7);
		   db.persist(ses8);
		   
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
			Map<String, String> properties = new HashMap<>();
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
	public Sesion getSesion(Date fecha, int salaNum){
		db.getTransaction().begin();
		fecha.setMinutes(0);
		fecha.setSeconds(0);
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
	    Calendar cal = Calendar.getInstance();
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    Date start = cal.getTime();

	    //se le suma 6 dias para tener el domingo
	    cal.add(Calendar.DATE, 6);
	    Date end = cal.getTime();

	    TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha BETWEEN :start AND :end ORDER BY s.fecha", Sesion.class);
	    query.setParameter("start", start);
	    query.setParameter("end", end);
	    List<Sesion> resultado = query.getResultList();

	    System.out.println(resultado);
	    for(Sesion ses: resultado) {
	        System.out.println(ses.getActividad().getNombre());
	    }

	    return resultado;
	}
	public List<Actividad> getActividades() {
		db.getTransaction().begin();
		System.out.println("Buscando las actividades en la base de datos");

		TypedQuery<Actividad> query = db.createQuery("SELECT a FROM Actividad a", Actividad.class);
		System.out.println(query);

		List<Actividad> resultado = query.getResultList();
		db.getTransaction().commit();
		return resultado;
	}

	public boolean addReserva(Sesion sesion, Usuario user) {
		db.getTransaction().begin();

		String idUsuario = user.getCorreo();
		Date idSesion = sesion.getFecha();

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
	    List<String> listaRes = usuario.getListaReservas();
		if(listaRes != null) {
			for(String r: listaRes) {

				if(r.equals(ses.getFecha()+"/"+ses.getSala().getNumero()+ "/"+user.getCorreo())){
				String[] reservas = r.split("/");
				if(reservas[0].equals(ses.getFecha()) && reservas[1].equals(Integer.toString(ses.getSala().getNumero()))){
					System.out.println("ya tienes esta sesion reservada");
					return false;
				}
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
//		System.out.println(usr.getListaReservas());
//		System.out.println(usr.getListaReservas().contains(ses.getFecha()+"/"+ses.getSala().getNumero()+ "/"+usr.getCorreo()));
		if(ses.getListaEspera().contains(usr)) {
			System.out.println("ya estas en la lista de espera");
			return ses;
		}
		else if(usr.getListaReservas().contains(ses.getFecha()+"/"+ses.getSala().getNumero()+ "/"+usr.getCorreo())) {
			System.out.println("ya tienes esa reserva");
			return ses;
		}
		else {
			ses.addAListaEspera(usr);
		}
		db.persist(ses);
		db.persist(usr);
		db.getTransaction().commit();
		return ses;
	}

	public boolean cancelarReserva(Sesion sesion, Usuario user) {
		System.out.println(sesion.getListaEspera());
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

		List<String> listaNomSesion = new ArrayList<>();
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

	public Sesion annadirSesion(Date fecha, int sala, String actividad, String precio, String plazas) {
		db.getTransaction().begin();
		Sesion res = null;
		Sala salaSes = db.find(Sala.class, sala);
		TypedQuery<Sesion> listaSesionesEnEsaFecha = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha = :fecha", Sesion.class);
	    listaSesionesEnEsaFecha.setParameter("fecha", fecha);
		List<Sesion> listaSes = listaSesionesEnEsaFecha.getResultList();
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		Date date = null;
//		try {
//			date = dateFormat.parse(fecha);
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}

		@SuppressWarnings("deprecation")
		int month = fecha.getMonth();
		Calendar fechaActual = Calendar.getInstance();
		int monthActual = fechaActual.get(Calendar.MONTH);

		if(listaSes != null) {
			for(Sesion ses: listaSes) {
				if(ses.getFecha() != null && ses.getFecha().equals(fecha) && ses.getSala() != null && ses.getSala().equals(salaSes)) {
					System.out.println("ya existe una sesion en esa sala en esa fecha");
				}
			}
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
			Actividad activ = db.find(Actividad.class, actividad);
			if(activ == null) {
				System.out.println("la actividad que has introducido no existe");
			}
			res = new Sesion(fecha, salaSes, Integer.parseInt(plazas), activ, Integer.parseInt(precio));
			if(res != null) System.out.println("la sesion se ha creado bien");
			salaSes.addAListaSesiones(res);
		}
		db.persist(res);
		db.persist(salaSes);
		db.getTransaction().commit();
		return res;
	}

	public Sesion quitarSesion(Date fecha, int salaNum) {
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
