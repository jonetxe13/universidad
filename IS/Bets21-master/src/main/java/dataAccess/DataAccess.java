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
	
	private Date annadirTiempo(Calendar cal, Date fecha, int dias, int horas) {
		System.out.println(fecha);
		   cal.setFirstDayOfWeek(Calendar.MONDAY);
		   cal.setTimeZone(TimeZone.getTimeZone("Europe/Madrid")); // establece la zona horaria en Madrid
		   cal.set(Calendar.MILLISECOND, 0);
		   cal.add(Calendar.DATE, dias); // agrega 1 día a la fecha actual
		   cal.add(Calendar.HOUR_OF_DAY, horas); // agrega 2 horas a la fecha actual
		   cal.set(Calendar.MINUTE, 0);
		   cal.set(Calendar.SECOND, 0);
		   fecha = cal.getTime();
		System.out.println(fecha);
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
		   Date fechaNueva = annadirTiempo(cal, null, 0, 1);

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
		   
		   fechaNueva = annadirTiempo(cal, fechaNueva, 0, 1);
		   Sesion ses2 = null; 
		   if (semanaActual == semanaSesion) {
			   System.out.println("la fecha de la base de datos :" + fechaNueva);
			  ses2 = new Sesion(fechaNueva, sala1, 10, act2, 4);
		   } else {
			    System.out.println("La fecha de la sesión no está dentro de la semana actual.");
		   }
		   
		   fechaNueva = annadirTiempo(cal, fechaNueva, 0, 1);
		   Sesion ses3 = null; 
		   if (semanaActual == semanaSesion) {
			   System.out.println("la fecha de la base de datos :" + fechaNueva);
			   ses3 = new Sesion(fechaNueva, sala1, 10, act2, 3);
		   } else {
			    System.out.println("La fecha de la sesión no está dentro de la semana actual.");
		   }
		   
		   fechaNueva = annadirTiempo(cal, fechaNueva, 0, 1);
		   Sesion ses4 = null; 
		   if (semanaActual == semanaSesion) {
			   	System.out.println("la fecha de la base de datos :" + fechaNueva);
			   ses4 = new Sesion(fechaNueva, sala2, 10, act3, 9);
		   } else {
			    System.out.println("La fecha de la sesión no está dentro de la semana actual.");
		   }
		   
		   fechaNueva = annadirTiempo(cal, fechaNueva, 0, 1);
		   Sesion ses5 = null; 
		   if (semanaActual == semanaSesion) {
			   System.out.println("la fecha de la base de datos :" + fechaNueva);
			   ses5 = new Sesion(fechaNueva, sala1, 10, act3, 13);
		   } else {
			    System.out.println("La fecha de la sesión no está dentro de la semana actual.");
		   }
		   
		   fechaNueva = annadirTiempo(cal, fechaNueva, 0, 1);
		   Sesion ses6 = null; 
		   if (semanaActual == semanaSesion) {
			   System.out.println("la fecha de la base de datos :" + fechaNueva);
			   ses6 = new Sesion(fechaNueva, sala1, 10, act1, 11);
		   } else {
			   System.out.println("La fecha de la sesión no está dentro de la semana actual.");
		   }
		   fechaNueva = annadirTiempo(cal, fechaNueva, 0, 1);
		   Sesion ses7 = null; 
		   if (semanaActual == semanaSesion) {
			   System.out.println("la fecha de la base de datos :" + fechaNueva);
			   ses7 = new Sesion(fechaNueva, sala1, 10, act1, 12);
		   } else {
			   System.out.println("La fecha de la sesión no está dentro de la semana actual.");
		   }
		   fechaNueva = annadirTiempo(cal, fechaNueva, 0, 1);
		   Sesion ses8 = null; 
		   if (semanaActual == semanaSesion) {
			   System.out.println("la fecha de la base de datos :" + fechaNueva);
			   ses8 = new Sesion(fechaNueva, sala1, 10, act1, 10);
		   } else {
			   System.out.println("La fecha de la sesión no está dentro de la semana actual.");
		   }
		   lista.add(sesion1);
		   lista.add(ses2);
		   lista.add(ses3);
		   lista.add(ses5);
		   lista.add(ses6);
		   lista.add(ses7);
		   lista.add(ses8);

		   sala1.setListaSesiones(lista);
		   
		   db.persist(usuario);
		   db.persist(usuario2);
		   
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
		   
		   this.addReserva(sesion1,usuario);
		   this.addReserva(ses2,usuario);
		   this.addReserva(ses3,usuario);
		   this.addReserva(ses4,usuario);
		   this.addReserva(ses5,usuario);
		   this.addReserva(ses6,usuario);
//		   Cargo prueba = new Cargo(usuario, sesion1);
//		   db.persist(prueba);
		   TypedQuery<Usuario> cargos = db.createQuery("SELECT c FROM Cargo c WHERE c.user=?1", Usuario.class);
		   cargos.setParameter(1, usuario);
		   System.out.println("el cargo1: \n" + cargos.getResultList());
		    
//		   System.out.println("\n el size al final es: " + usuario.getListaReservas().size() + "\n");
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
			db.getTransaction().commit();
		}
		return user;
	}
	public boolean userExists(Usuario newUser) {
		//busca en la base de datos y devuelve true si existe y false si no
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
//		db.getTransaction().begin();
		System.out.println("la fecha en getSesion en dataaccess es: " + fecha.toString());
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
//		db.persist(ses);
//		db.getTransaction().commit();
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

	@SuppressWarnings("deprecation")
	public List<Sesion> getSesionesSemana() {
		System.out.println("Buscando las sesiones de esta semana en la base de datos");
	    //coge el inicio y el fin la semana en curso en start el lunes y en end el domingo
	    Calendar cal = Calendar.getInstance();
	    cal.setFirstDayOfWeek(Calendar.MONDAY);
	    cal.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
	    Date start = cal.getTime();
	    start.setHours(0);
	    start.setMinutes(0);
	    start.setSeconds(0);

	    //se le suma 6 dias para tener el domingo
	    cal.add(Calendar.DATE, 6);
	    Date end = cal.getTime();
	    end.setHours(23);
	    end.setMinutes(0);
	    end.setSeconds(0);
	    //busca las sesiones con una fecha entre esos valores
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
		db.getTransaction().begin();

		String idUsuario = user.getCorreo();
		Date idSesion = sesion.getFecha();

		//busca la sesion y el usuario pasado como parametro
		Usuario usuario = db.find(Usuario.class, idUsuario);
		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha=:fecha", Sesion.class);
	    query.setParameter("fecha", idSesion);
	    List<Sesion> sesiones = query.getResultList();
	    Sesion ses = null;
	    //busca la sesion en concreto
	    for(Sesion sesion2: sesiones) {
	    	if(sesion2.getSala().getNumero() == sesion.getSala().getNumero()) {
	    		ses = sesion2;
	    	}
	    }
	    //coge la lista de reservas del usuario
	    List<String> listaRes = usuario.getListaReservas();
//	    System.out.println("la lista de reservas: " + listaRes);
		if(listaRes != null) {
			for(String r: listaRes) {
				//si en la lista de reservas esta el codigo que se crearia con esa sesion y ese usuario entonces es que ya tienes esa reserva
				if(r.equals(ses.getFecha()+"/"+ses.getSala().getNumero()+ "/"+user.getCorreo())){
					System.out.println("ya tienes esta sesion reservada");
					return false;
				}
			}
		}
		//se crea el codigo que identifica la reserva
		String codigo = ses.crearHash(user);
		//se annade el codigo a la lista de reservas del usuario
		usuario.addReserva(codigo);
		ses.setPlazasDisponibles(ses.getPlazasDisponibles()-1);
		Cargo cargoUser = null;
		if(listaRes.size() == 5) {
			for(String reserva: listaRes) {
				String[] reservaSplit = reserva.split("/");
				Date date = null;
				try {
					date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(reservaSplit[0]);
				} catch (ParseException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				Sesion sesi = getSesion(date, Integer.parseInt(reservaSplit[1]));
				cargoUser = new Cargo();
				CargoId cargoId = new CargoId();
				cargoId.setUser(usuario);
				cargoId.setSesion(sesi);
				cargoUser.setId(cargoId);
				System.out.println("cargo es nul2?? : " + cargoUser + "\n");
				db.persist(cargoUser);
			}
		} 
		else if(listaRes.size() > 5) {
			System.out.println("El size es >5??: \n" + listaRes.size() + "\n");
			String[] codigoSplit = codigo.split("/");
			Date date = null;
			try {
				date = new SimpleDateFormat("EEE MMM dd HH:mm:ss zzz yyyy", Locale.ENGLISH).parse(codigoSplit[0]);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			Sesion sesi = getSesion(date, Integer.parseInt(codigoSplit[1]));
			cargoUser = new Cargo();
			CargoId cargoId = new CargoId();
			cargoId.setUser(usuario);
			cargoId.setSesion(sesi);
			cargoUser.setId(cargoId);
			System.out.println("cargo es nul2?? : " + cargoUser + "\n");
			db.persist(cargoUser);
		}
//		System.out.println("\n el size es: " + listaRes.size() + "\n");

		db.persist(usuario);
		db.persist(ses);
		db.getTransaction().commit();
		return true;
	}

	public Sesion addAListaEspera(Sesion sesion, Usuario user) {
		db.getTransaction().begin();
		
		//buscar la sesion
		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha=:fecha", Sesion.class);
	    query.setParameter("fecha", sesion.getFecha());
	    List<Sesion> sesiones = query.getResultList();
	    Sesion ses = null;
	    for(Sesion sesion2: sesiones) {
	    	if(sesion2.getSala().getNumero() == sesion.getSala().getNumero()) {
	    		ses = sesion2;
	    	}
	    }
	    //buscar el usuario
		Usuario usr = db.find(Usuario.class, user.getCorreo());
		//si la lista de espera contiene al usuario es que ya estas en la lista de espera
		if(ses.getListaEspera().contains(usr)) {
			System.out.println("ya estas en la lista de espera");
			return ses;
		}
		//si en la lista de reservas tienes el codigo que se crearia al reservarla es que ya tienes la reserva
		else if(usr.getListaReservas().contains(ses.getFecha()+"/"+ses.getSala().getNumero()+ "/"+usr.getCorreo())) {
			System.out.println("ya tienes esa reserva");
			return ses;
		}
		else {
			//se annade a la lista de espera de la sesion
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
		//buscar la sesion
		TypedQuery<Sesion> query = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha=:fecha", Sesion.class);
	    query.setParameter("fecha", sesion.getFecha());
	    List<Sesion> sesiones = query.getResultList();
	    Sesion ses = null;
	    for(Sesion sesion2: sesiones) {
	    	if(sesion2.getSala().getNumero() == sesion.getSala().getNumero()) {
	    		ses = sesion2;
	    	}
	    }
	    boolean res = false;
	    //buscar el usuario
		Usuario usr = db.find(Usuario.class, user.getCorreo());

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
		Sesion res = null;
		//buscar la sala
		Sala salaSes = db.find(Sala.class, sala);
		//buscar las sesiones en esa fecha
		TypedQuery<Sesion> listaSesionesEnEsaFecha = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha = :fecha", Sesion.class);
	    listaSesionesEnEsaFecha.setParameter("fecha", fecha);
		List<Sesion> listaSes = listaSesionesEnEsaFecha.getResultList();

		@SuppressWarnings("deprecation")
		int month = fecha.getMonth();
		Calendar fechaActual = Calendar.getInstance();
		int monthActual = fechaActual.get(Calendar.MONTH);
		//si se encuentra una con la misma fecha y sala es que ya existe una sesion
		if(listaSes != null) {
			for(Sesion ses: listaSes) {
				if(ses.getFecha() != null && ses.getFecha().equals(fecha) && ses.getSala() != null && ses.getSala().equals(salaSes)) {
					System.out.println("ya existe una sesion en esa sala en esa fecha");
				}
			}
		}
		//si la sala es null es que no existe esa sala
		if(salaSes == null) {
			System.out.println("no se ha encontrado esa sala");
		}
		else if(month != monthActual) {
			System.out.println("el mes no es el mes en el que te encuentras");
			System.out.println(month + " " + monthActual);
		}
		else {
			//busca la actividad
			Actividad activ = db.find(Actividad.class, actividad);
			if(activ == null) {
				System.out.println("la actividad que has introducido no existe");
			}
			//si existe la actividad se intenta crear la sesion
			res = new Sesion(fecha, salaSes, Integer.parseInt(plazas), activ, Integer.parseInt(precio));
			if(res != null) System.out.println("la sesion se ha creado bien");
			//se annade la sesion a la sala
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
		//bucar las sesiones en esa fecha
		TypedQuery<Sesion> listaSesionesEnEsaFecha = db.createQuery("SELECT s FROM Sesion s WHERE s.fecha = :fecha", Sesion.class);
	    listaSesionesEnEsaFecha.setParameter("fecha", fecha);
		List<Sesion> listSesion = listaSesionesEnEsaFecha.getResultList();
		for(Sesion ses: listSesion) {
			System.out.println(ses.getFecha());
			//si el numero de alguna de esas salas es el mismo que el del argumento es que existe la sesion y se borra
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
		//buscar la actividad
		Actividad act = db.find(Actividad.class, nombre);
		//si no es null entonces existe, si es null se crea una nueva con esos parametros
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
