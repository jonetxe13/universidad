package businessLogic;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;

import domain.Actividad;
import domain.Encargado;
import domain.Sala;
import domain.Sesion;
import domain.Usuario;
import exceptions.EventFinished;
import exceptions.QuestionAlreadyExist;

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
	 * This method calls the data access to initialize the database with some events and questions.
	 * It is invoked only when the option "initialize" is declared in the tag dataBaseOpenMode of resources/config.xml file
	 */
	@WebMethod public void initializeBD();
	@WebMethod public Sala getSala(String string);
	@WebMethod public List<Sesion> sesionesSemana();
	@WebMethod public List<Actividad> getActividades();
	@WebMethod public boolean addReserva(Sesion seleccionado, Usuario user);
	@WebMethod public  Sesion addAListaEspera(Sesion sesion, Usuario user);
	@WebMethod public Sesion getSesion(String ses, int integer);
	@WebMethod public boolean cancelarReserva(Sesion sesion, Usuario user);
	@WebMethod public Sesion annadirSesion(String text, String string, String text3, String text4, String text5);
	@WebMethod public Sesion quitarSesion(String text, String text2);
	@WebMethod public Actividad annadirActividad(String text, String text2, String text3);

}
