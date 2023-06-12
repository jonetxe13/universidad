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
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
//import javax.swing.text.html.HTMLDocument.Iterator;

import configuration.ConfigXML;
import domain.Actividad;
import domain.Cargo;
import domain.CargoId;
import domain.Encargado;
import domain.Factura;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;

/**
 * It implements the data access to the objectDb database
 */
public class DataAccess  {
	protected static EntityManager  db;
	protected static EntityManagerFactory emf;
	private int numFactura = 1;


	ConfigXML c=ConfigXML.getInstance();

     public DataAccess(boolean initializeMode)  {
		System.out.println("Creating DataAccess instance => isDatabaseLocal: "+c.isDatabaseLocal()+" getDatabBaseOpenMode: "+c.getDataBaseOpenMode());

		open(initializeMode);
	}

	private boolean esLunes() {
		Calendar cal = Calendar.getInstance();
		return cal.get(Calendar.DAY_OF_WEEK) == Calendar.MONDAY;
	}

	private void crearFacturas() {
		db.getTransaction().begin();
		List<Usuario> listaUsuarios = getListaUserCargos();
		for(int i = 0; i < listaUsuarios.size(); i++ ) {
			List<Cargo> listaSesion = getListaCargos(listaUsuarios.get(i));
			Factura factura = new Factura(listaUsuarios.get(i), listaSesion, numFactura);
			System.out.println(factura.getCodigo());
			db.persist(factura);
			numFactura++;
		}
		db.getTransaction().commit();
	}

	public DataAccess()  {
		 this(false);
	}
	
	private Date annadirTiempo(Calendar cal, Date fecha, int dias, int horas) {
//		System.out.println(fecha);
		   cal.setFirstDayOfWeek(Calendar.MONDAY);
		   cal.setTimeZone(TimeZone.getTimeZone("Europe/Madrid")); // establece la zona horaria en Madrid
		   cal.set(Calendar.MILLISECOND, 0);
		   cal.add(Calendar.DATE, dias); // agrega 1 día a la fecha actual
		   cal.add(Calendar.HOUR_OF_DAY, horas); // agrega 2 horas a la fecha actual
		   cal.set(Calendar.MINUTE, 0);
		   cal.set(Calendar.SECOND, 0);
		   fecha = cal.getTime();
//		System.out.println(fecha);
		   return fecha;
	}


	/**
	 * This is the data access method that initializes the database with some events and questions.
	 * This method is invoked by the business logic (constructor of BLFacadeImplementation) when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	public void initializeDB(){

		db.getTransaction().begin();
		try {
		   Calendar cal = Calendar.getInstance();

		   Actividad act1 = new Actividad("zumba", 3, 5);
		   Actividad act2 = new Actividad("pilates", 2, 15);
		   Actividad act3 = new Actividad("crossfit", 5, 25);

		   db.persist(act1);
		   db.persist(act2);
		   db.persist(act3);
		   
		   Sala sala1 = new Sala(1, 20);
		   Sala sala2 = new Sala(2, 20);
		   
		   //inicializamos la fecha
		   Date fechaNueva = annadirTiempo(cal, null, 0, 0);

		   System.out.println("La nueva fecha es: " + fechaNueva);

		   Calendar cal2 = Calendar.getInstance();
		   int semanaActual = cal2.get(Calendar.WEEK_OF_YEAR);
		   cal2.setTime(fechaNueva);
		   int semanaSesion = cal2.get(Calendar.WEEK_OF_YEAR);
		   Sesion sesion1 = null; 
		   if (semanaActual == semanaSesion) {
			  sesion1 = new Sesion(fechaNueva, sala1, 1, act1, 100);
		   } else {
			    System.out.println("La fecha de la sesión no está dentro de la semana actual.");
		   }

		   Encargado admin = Encargado.getInstance("admin@admin.com", "admin");
		   System.out.println("se ha annadido al encargado");
		   db.persist(admin);

		   Usuario usuario = new Usuario("a@a.com", "nose");
		   Usuario usuario2 = new Usuario("b@b.com", "nose");
		   System.out.println("se ha annadido el usuario");
		   
		   List<Sesion> lista = new ArrayList<>();
		   lista.add(sesion1);
		   db.persist(usuario);
		   db.persist(usuario2);
		   
		   db.persist(sala1);
		   db.persist(sala2);
		   
		   db.persist(new Cargo());
		   db.getTransaction().commit();
		   
		   //annadir sesiones para esta semana y para la siguiente
		   Date fechaNueva2 = annadirTiempo(cal, fechaNueva, -12, 0);
		   
		   System.out.println("\nla primera fecha es: " + fechaNueva);
		   System.out.println("\nla segunda fecha es: " + fechaNueva2);
		   for(int i = 0; i < 8; i++) {
			   db.getTransaction().begin();
			   fechaNueva = annadirTiempo(cal, fechaNueva, 1, 0);
			   Sesion sesion = new Sesion(fechaNueva, sala1, 1+i, act2, 4); 
			   Sesion sesion2 = new Sesion(fechaNueva2, sala1, 1+i, act2, 4); 
			   fechaNueva2 = annadirTiempo(cal, fechaNueva2, 1, 0);
			   db.persist(sesion);
			   db.persist(sesion2);
			   db.getTransaction().commit();
			   this.addReserva(sesion, usuario);
			   this.crearCargo(usuario, sesion.crearHash(usuario));
			   System.out.println("\n\n\n" + sesion.crearHash(usuario)+ "\n\n\n");
			   lista.add(sesion);
			   
			   usuario.addReserva(sesion2.crearHash(usuario));
		   }
		   db.getTransaction().begin();
		   System.out.println("\nla primera fecha es despues: " + fechaNueva);
		   System.out.println("\nla segunda fecha es despues: " + fechaNueva2);

		   sala1.setListaSesiones(lista);
		   db.persist(sala1);
		   db.getTransaction().commit();
		   getListaSesionCargos(usuario);
	    
		if(!esLunes()) {
			crearFacturas();
		}
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
		//comprueba si existe el usuario o no, si existe te lo devuelve y si no existe lo crea (actua tambien como getUsuario)
		db.getTransaction().begin();
		Usuario user = db.find(Usuario.class, correo);
		if(user == null) {
			System.out.print("el usuario no existe asi que se crea");
			user = new Usuario(correo, contrasenna);
			db.persist(user);
		}
		db.getTransaction().commit();
		return user;
	}
	public Usuario getUsuario(String correo) {
		return db.find(Usuario.class, correo);
	}
	public boolean userExists(String correo) {
		//busca en la base de datos y devuelve true si existe y false si no
		System.out.println("Buscando el usuario en la base de datos");
		Usuario usuario = db.find(Usuario.class, correo);
		if(usuario == null) {
			System.out.println("\n  no contiene a ese usuario");
			return false;
		}
		System.out.println("Si que contiene el usuario");
		return true;
	}
	public boolean encargadoExists(Encargado enc) {
		//busca al encargado y devuelve si existe o no
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
		//devuelve el encargado
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
		//busca en la base de datos las sesiones con la misma fecha
		TypedQuery<Sesion> listaSes = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha=:fecha", Sesion.class);
		listaSes.setParameter("fecha", fecha);
		List<Sesion> listaSesiones = listaSes.getResultList();
		Sesion ses = null;
		//itera sobre la lista de sesiones hasta encontrar la sesion con la misma sala y fecha especificadas
		for(Sesion sesion: listaSesiones) {
			if(sesion.getSala().getNumero() == salaNum) {
				ses = sesion;
			}
		}
		if(ses == null) {
			System.out.print("la sesion no existe\n");
			return null;
		}
		return ses;
	}

	public Sala getSala(String string) {
		//devuelve la sala
		System.out.println("Buscando la sala en la base de datos");
		db.getTransaction().begin();
		Sala res = db.find(Sala.class, string);
		if(res == null) return null;
		return res;
	}

	public List<Sesion> getSesionesSemana() {
		System.out.println("Buscando las sesiones de esta semana en la base de datos");
	    //coge el inicio y el fin la semana en curso en start el lunes y en end el domingo
	    Calendar cal = Calendar.getInstance();
	    cal.setFirstDayOfWeek(Calendar.MONDAY);
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    Date start = cal.getTime();
//	    start = annadirTiempo(cal, start, 0, 0);
	    start = annadirTiempo(cal, start, -7, 0);

	    //se le suma 6 dias para tener el domingo
	    Date end = annadirTiempo(cal, start, 13, 0);
	    
	    //busca las sesiones con una fecha entre esos valores
	    TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha BETWEEN :start AND :end ORDER BY s.fecha", Sesion.class);
	    query.setParameter("start", start);
	    query.setParameter("end", end);
	    List<Sesion> resultado = query.getResultList();

	    return resultado;
	}
	public List<Actividad> getActividades() {
		//coge los tipos de actividades que hay
		db.getTransaction().begin();
		System.out.println("Buscando las actividades en la base de datos");

		TypedQuery<Actividad> query = db.createQuery("SELECT a FROM Actividad a", Actividad.class);
		System.out.println(query);

		List<Actividad> resultado = query.getResultList();
		db.getTransaction().commit();
		return resultado;
	}

	public boolean addReserva(Sesion sesion, Usuario user) {
		System.out.println(db.isOpen());
		db.getTransaction().begin();
		System.out.println(db.isOpen());

		String idUsuario = user.getCorreo();
		
		//busca la sesion y el usuario pasado como parametro
		Usuario usuario = db.find(Usuario.class, idUsuario);
		Sesion ses = getSesion(sesion.getFecha(), sesion.getSala().getNumero());
	    //coge la lista de reservas del usuario
	    List<String> listaRes = usuario.getListaReservas();
		if(listaRes != null) {
			for(String r: listaRes) {
				//si en la lista de reservas esta el codigo que se crearia con esa sesion y ese usuario entonces es que ya tienes esa reserva
				if(r.equals(ses.crearHash(user))){
					System.out.println("ya tienes esta sesion reservada");
					return false;
				}
			}
		}
		//se crea el codigo que identifica la reserva
		String codigo = ses.crearHash(user);
		//se annade el codigo a la lista de reservas del usuario
		usuario.addReserva(codigo);
		System.out.println(codigo);
		ses.setPlazasDisponibles(ses.getPlazasDisponibles()-1);

		db.persist(usuario);
		db.persist(ses);
		db.getTransaction().commit();
		return true;
	}

	public void crearCargo(Usuario usuario, String codigo) {
		if(db.getTransaction().isActive()) db.getTransaction().commit();
		db.getTransaction().begin();
		try {
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setFirstDayOfWeek(Calendar.MONDAY);
	    cal2.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    Date start = cal2.getTime();
	    start = annadirTiempo(cal2, start, -7, 0);

	    //se le suma 6 dias para tener el domingo
	    Date end = cal2.getTime();
	    end = annadirTiempo(cal2, end, 6, 0);
		Cargo cargoUser = null;
		if(usuario.getListaReservas().size() == 5) {
			for(String reserva: usuario.getListaReservas()) {
				String[] reservaSplit = reserva.split("/");
				Date date = null;
				try {
					date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(reservaSplit[0]);
				} catch (ParseException e) {
					e.printStackTrace();
				}
				if(date.compareTo(end) <= 0 && date.compareTo(start) >= 0) {
					Sesion sesi = getSesion(date, Integer.parseInt(reservaSplit[1]));
					cargoUser = new Cargo();
					CargoId cargoId = new CargoId();
					cargoId.setUser(usuario);
					cargoId.setSesion(sesi);
					cargoUser.setId(cargoId);
					db.persist(cargoUser);
				}
			}
		} 
		else if(usuario.getListaReservas().size() > 5) {
			String[] codigoSplit = codigo.split("/");
			Date date = null;
			try {
				date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(codigoSplit[0]);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			if(date.compareTo(end) < 0 && date.compareTo(start) > 0) {
				Sesion sesi = getSesion(date, Integer.parseInt(codigoSplit[1]));
				cargoUser = new Cargo();
				CargoId cargoId = new CargoId();
				cargoId.setUser(usuario);
				cargoId.setSesion(sesi);
				cargoUser.setId(cargoId);
				db.persist(cargoUser);
			}
		}
        db.getTransaction().commit();
		}
		catch(Exception e) {
			System.out.println(e);
			if(db.getTransaction().isActive())db.getTransaction().rollback();
		}
	}

	public Sesion addAListaEspera(Sesion sesion, Usuario user) {
		db.getTransaction().begin();
		//buscar la sesion y el usuario
	    Sesion ses = getSesion(sesion.getFecha(), sesion.getSala().getNumero());
		Usuario usr = getUsuario(user.getCorreo());
		try {
		//si la lista de espera contiene al usuario es que ya estas en la lista de espera
		if(ses.getListaEspera().contains(usr)) {
			System.out.println("ya estas en la lista de espera");
		}
		//si en la lista de reservas tienes el codigo que se crearia al reservarla es que ya tienes la reserva
		else if(usr.getListaReservas().contains(ses.crearHash(user))) {
			System.out.println("ya tienes esa reserva");
		}
		else {
			//se annade a la lista de espera de la sesion
			ses.addAListaEspera(usr);
		}
		db.persist(ses);
		db.persist(usr);
		db.getTransaction().commit();
    } catch (Exception e) {
        db.getTransaction().rollback();
        e.printStackTrace();
    }		
		return ses;
	}

	public boolean cancelarReserva(Sesion sesion, Usuario user) {
		System.out.println(sesion.getListaEspera());
		db.getTransaction().begin();
		//buscar la sesion
	    Sesion ses = getSesion(sesion.getFecha(), sesion.getSala().getNumero());
	    
	    boolean res = false;
	    //buscar el usuario
		Usuario usr = getUsuario(user.getCorreo());

		List<String> listaNomSesion = new ArrayList<>();
		List<String> listaReservas = usr.getListaReservas();
		//se mira en la lista de reservas a ver si ya la tienes
		Iterator<String> iterator = listaReservas.iterator();
		while (iterator.hasNext()) {
		    String s = iterator.next();
		    String[] nomSesion = s.split("/");
		    //coges solo la fecha de la sesion
		    listaNomSesion.add(nomSesion[0]);
		    
		    //si la fecha y la sesion coinciden es que es esa
		    if (nomSesion[0].equals(new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).format(ses.getFecha())) && Integer.parseInt(nomSesion[1]) == ses.getSala().getNumero()) {
		        System.out.println("se ha encontrado la sesion con el mismo nombre que el de la reserva");
		        //la quitas de la lista de reservas del usuario
		        iterator.remove();
		        //le sumas una plaza a la sesion
		        ses.setPlazasDisponibles(ses.getPlazasDisponibles() + 1);
		        res = true;
	        	Usuario user2 = ses.removeDeListaEspera();
		        if(user2 != null) {
		        	System.out.println(user2);
		        	//coges a la primera persona que se haya metido a la lista de espera de la sesion y le annades la reserva, con el codigo
		        	user2.addReserva(ses.crearHash(user2));
		        	//le quitas una plaza a la sesion
		        	ses.setPlazasDisponibles(ses.getPlazasDisponibles()-1);
		        }
		    }
		}
		db.persist(usr);
		db.persist(ses);
		db.getTransaction().commit();
		return res;
	}

	public Sesion annadirSesion(Date fecha, int sala, String actividad, String precio, String plazas) {
		db.getTransaction().begin();
		//buscar la sala y la sesion
		Sala salaSes = db.find(Sala.class, sala);
		Sesion sesion = getSesion(fecha, sala);

		@SuppressWarnings("deprecation")
		int month = fecha.getMonth();
		Calendar fechaActual = Calendar.getInstance();
		int monthActual = fechaActual.get(Calendar.MONTH);
		
		//si la sala es null es que no existe esa sala
		if(salaSes == null) {
			System.out.println("no se ha encontrado esa sala");
		}
		else if(month != monthActual) {
			System.out.println("el mes no es el mes en el que te encuentras");
			System.out.println(month + " " + monthActual);
		}
		else if(sesion != null) {
			System.out.println("la sesion ya existe");
		}
		else {
			//busca la actividad
			Actividad activ = db.find(Actividad.class, actividad);
			if(activ == null) {
				System.out.println("la actividad que has introducido no existe");
			}
			//si existe la actividad se intenta crear la sesion
			sesion = new Sesion(fecha, salaSes, Integer.parseInt(plazas), activ, Integer.parseInt(precio));
			if(sesion != null) System.out.println("la sesion se ha creado bien");
			//se annade la sesion a la sala
			salaSes.addAListaSesiones(sesion);
		}
		db.persist(sesion);
		db.persist(salaSes);
		db.getTransaction().commit();
		return sesion;
	}

	public Sesion quitarSesion(Date fecha, int salaNum) {
		db.getTransaction().begin();
		Sesion sesion = getSesion(fecha, salaNum);
		db.remove(sesion);
		db.getTransaction().commit();
		return sesion;
	}

	public Actividad annadirActividad(String nombre, int nvl, int prc) {
		db.getTransaction().begin();
		//buscar la actividad
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
	public List<Usuario> getListaUserCargos() {
	    TypedQuery<Usuario> cargos = db.createQuery("SELECT DISTINCT c.user FROM Cargo c WHERE c.user.correo is not null", Usuario.class);
	    List<Usuario> listaCargos = cargos.getResultList();
	    for(Usuario user: listaCargos) {
	    	System.out.println("listaUsuarios: " + user.getCorreo());
	    }
	    return listaCargos;
	}
	public List<Sesion> getListaSesionCargos(Usuario user) {
	    Usuario usuario = this.getUsuario(user.getCorreo());
	    TypedQuery<Sesion> sesiones = db.createQuery("SELECT s FROM Cargo c JOIN c.sesion s WHERE c.user = :user", Sesion.class);
	    sesiones.setParameter("user", usuario);
	    List<Sesion> listaSesiones = sesiones.getResultList();
	    System.out.println(listaSesiones);
	    
	    return listaSesiones;
	}

	public List<Cargo> getListaCargos(Usuario user) {
	    Usuario usuario = this.getUsuario(user.getCorreo());
	    TypedQuery<Cargo> cargos = db.createQuery("SELECT c FROM Cargo c WHERE c.user = :user", Cargo.class);
	    cargos.setParameter("user", usuario);
	    List<Cargo> listaCargos= cargos.getResultList();
	    
	    return listaCargos;
	}
	public void eliminarCargo(Usuario user, Sesion sesion) {
	    Usuario usuario = this.getUsuario(user.getCorreo());
	    TypedQuery<Cargo> cargos = db.createQuery("SELECT c FROM Cargo c WHERE c.user = :user", Cargo.class);
	    cargos.setParameter("user", usuario);
		db.getTransaction().begin();
		Sesion ses = getSesion(sesion.getFecha(), sesion.getSala().getNumero());
	    List<Cargo> listaCargos = cargos.getResultList();
	    for(Cargo c: listaCargos) {
	    	if(c.getSesion() == ses) {
	    		db.remove(c);
	    	}
	    }
		db.getTransaction().commit();
	}
	public Factura getFactura(Usuario user) {
	    Usuario usuario = this.getUsuario(user.getCorreo());
	    TypedQuery<Factura> facturas = db.createQuery("SELECT f FROM Factura f WHERE f.codigo LIKE '" + user.getCorreo()+ "%'", Factura.class);
	    facturas.setParameter("user", usuario.getCorreo());
	    Factura fact = facturas.getSingleResult();
	    
	    return fact;
	}
}
