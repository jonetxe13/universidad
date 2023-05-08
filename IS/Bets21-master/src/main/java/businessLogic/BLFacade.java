package businessLogic;

import java.util.Vector;
import java.util.Date;
import java.util.List;

//import domain.Booking;
import domain.Question;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;
import domain.Encargado;
import domain.Event;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

import javax.jws.WebMethod;
import javax.jws.WebService;

/**
 * Interface that specifies the business logic.
 */
@WebService
public interface BLFacade  {
	  

	/**
	 * This method creates a question for an event, with a question text and the minimum bet
	 * 
	 * @param event to which question is added
	 * @param question text of the question
	 * @param betMinimum minimum quantity of the bet
	 * @return the created question, or null, or an exception
	 * @throws EventFinished if current data is after data of the event
 	 * @throws QuestionAlreadyExist if the same question already exists for the event
	 */
//	@WebMethod Question createQuestion(Event event, String question, float betMinimum) throws EventFinished, QuestionAlreadyExist;
	@WebMethod public Usuario createUsuario(String correo, String contrasenna);
	@WebMethod public boolean userExists(String text, String text2);
	@WebMethod public boolean encargadoExists(String text, String text2);
	@WebMethod public Encargado getEncargado(String text, String text2);
	
	/**
	 * This method retrieves the events of a given date 
	 * 
	 * @param date in which events are retrieved
	 * @return collection of events
	 */
//	@WebMethod public Vector<Event> getEvents(Date date);
	
	/**
	 * This method retrieves from the database the dates a month for which there are events
	 * 
	 * @param date of the month for which days with events want to be retrieved 
	 * @return collection of dates
	 */
//	@WebMethod public Vector<Date> getEventsMonth(Date date);
	
	/**
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */	
	@WebMethod public void initializeBD();
	@WebMethod public Sala getSala(String string);
	@WebMethod public List<Sesion> sesionesSemana();
	@WebMethod public boolean addReserva(Sesion seleccionado, Usuario user);
	@WebMethod public  Sesion addAListaEspera(Sesion sesion, Usuario user);
	@WebMethod public Sesion getSesion(String ses);
	@WebMethod public boolean cancelarReserva(Sesion sesion, Usuario user);
	@WebMethod public Sesion annadirSesion(String text, String string, String text3, String text4, String text5);
	@WebMethod public Sesion quitarSesion(String text, String text2);

	
}
