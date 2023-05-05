package dataAccess;

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

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import configuration.ConfigXML;
import configuration.UtilDate;
import domain.Actividad;
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
		
//		db.getTransaction().begin();
//		try {
//
//			
//		   Calendar today = Calendar.getInstance();
//		   
//		   int month=today.get(Calendar.MONTH);
//		   int year=today.get(Calendar.YEAR);
//		   if (month==12) { month=0; year+=1;}  
//	    
//		   Sala sala1 = new Sala("zumba", 20);
//		   SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
//		   Calendar cal = Calendar.getInstance();
//		   String fecha = sdf.format(cal.getTime());
//		   
//		   Usuario master = new Usuario("a@a.com", "nose");
//		   System.out.println("se ha annadido el usuario");
//		   db.persist(master);
//		   
//		   List<Sesion> lista = new ArrayList<Sesion>();
//		   Sesion sesion1 = new Sesion(fecha, 20, sala1);
//		   lista.add(sesion1);
//		   cal.add(Calendar.DATE, 1);
//		   for(int i = 0; i < 20; i++) {			   
//			   String fecha2 = sdf.format(cal.getTime());
//			   Sesion sesionNueva = new Sesion(fecha2, 20+i, sala1);
//			   lista.add(sesionNueva);
//			   db.persist(sesionNueva);
//			}
//
//		   
//		   Actividad act1 = new Actividad("zumba", 3, (float) 5.5);
//		   Actividad act2 = new Actividad("pilates", 2, (float) 15.5);
//		   Actividad act3 = new Actividad("crossfit", 5, (float) 25.5);
//		   db.persist(act1);
//		   db.persist(act2);
//		   db.persist(act3);
//		   List<Actividad> listaActividades = new ArrayList<Actividad>();
//		   listaActividades.add(act1);
//		   listaActividades.add(act2);		
//		   listaActividades.add(act3);
//		   sesion1.setListaActividades(listaActividades);
//		   db.persist(sesion1);
//		   lista.add(sesion1);	
//		   
//		   sala1.setListaSesiones(lista);
//			db.persist(sala1);
//			
//			db.getTransaction().commit();
//		}
//		catch (Exception e){
//			e.printStackTrace();
//		}
//		System.out.println("Db initialized");
	}
	
	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
	public Question createQuestion(Event event, String question, float betMinimum) throws  QuestionAlreadyExist {
		System.out.println(">> DataAccess: createQuestion=> event= "+event+" question= "+question+" betMinimum="+betMinimum);
		
			Event ev = db.find(Event.class, event.getEventNumber());
			
			if (ev.DoesQuestionExists(question)) throw new QuestionAlreadyExist(ResourceBundle.getBundle("Etiquetas").getString("ErrorQueryAlreadyExist"));
			
			db.getTransaction().begin();
			Question q = ev.addQuestion(question, betMinimum);
			//db.persist(q);
			db.persist(ev); // db.persist(q) not required when CascadeType.PERSIST is added in questions property of Event class
							// @OneToMany(fetch=FetchType.EAGER, cascade=CascadeType.PERSIST)
			db.getTransaction().commit();
			return q;
		
	}
	
	/**
	 * This method retrieves from the database the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
	public Vector<Event> getEvents(Date date) {
		System.out.println(">> DataAccess: getEvents");
		Vector<Event> res = new Vector<Event>();	
		TypedQuery<Event> query = db.createQuery("SELECT ev FROM Event ev WHERE ev.eventDate=?1",Event.class);   
		query.setParameter(1, date);
		List<Event> events = query.getResultList();
	 	 for (Event ev:events){
	 	   System.out.println(ev.toString());		 
		   res.add(ev);
		  }
	 	return res;
	}
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
	public Vector<Date> getEventsMonth(Date date) {
		System.out.println(">> DataAccess: getEventsMonth");
		Vector<Date> res = new Vector<Date>();	
		
		Date firstDayMonthDate= UtilDate.firstDayMonth(date);
		Date lastDayMonthDate= UtilDate.lastDayMonth(date);
				
		
		TypedQuery<Date> query = db.createQuery("SELECT DISTINCT ev.eventDate FROM Event ev WHERE ev.eventDate BETWEEN ?1 and ?2",Date.class);   
		query.setParameter(1, firstDayMonthDate);
		query.setParameter(2, lastDayMonthDate);
		List<Date> dates = query.getResultList();
	 	 for (Date d:dates){
	 	   System.out.println(d.toString());		 
		   res.add(d);
		  }
	 	return res;
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

			  emf = Persistence.createEntityManagerFactory("objectdb://"+c.getDatabaseNode()+":"+c.getDatabasePort()+"/"+fileName, properties);

			  db = emf.createEntityManager();
    	   }
		
	}
	public boolean existQuestion(Event event, String question) {
		System.out.println(">> DataAccess: existQuestion=> event= "+event+" question= "+question);
		Event ev = db.find(Event.class, event.getEventNumber());
		return ev.DoesQuestionExists(question);
		
	}
	public void close(){
		db.close();
		System.out.println("DataBase closed");
	}
	public Usuario createUsuario(String correo, String contrasenna){
		System.out.println(">> DataAccess: createUsuario=> correo= "+correo+" contrasenna= "+contrasenna);

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
//		System.out.print(usuario.getCorreo());
		if(usuario == null) {
			System.out.println("\n  no contiene a ese usuario");
			return false;
		}
		System.out.println("Si que contiene el usuario");
		return true;
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
//		System.out.println(resultado);
		return resultado;
	}

	public boolean addReserva(Sesion sesion, Usuario user) {		  
		db.getTransaction().begin();
		
		String idUsuario = user.getCorreo();
		String idSesion = sesion.getFecha();
		
		Usuario usuario = db.find(Usuario.class, idUsuario);
		Sesion ses = db.find(Sesion.class, idSesion);
		if(ses.getPlazasDisponibles() == 0) {
			System.out.println("No se puede reservar porque esta llena");
			return false;
		}
		System.out.println(usuario.getListaReservas());
		if(usuario.getListaReservas() != null) {
			for(String r: user.getListaReservas()) {
				
//				System.out.println(r);
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
}
