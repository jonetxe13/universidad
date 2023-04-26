package logicaNegocio;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import domain.Cuenta;
public class LoginODB implements Login {
String fileName="passwords.odb";
private EntityManager db;
private EntityManagerFactory emf;
public LoginODB() {
emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
db = emf.createEntityManager();
System.out.println("Base de datos abierta");
inicializarBD();
}
public void inicializarBD () {
add("Kepa","hola","Profesor");
add("Nerea","kaixo","Estudiante"); 
}
public boolean add(String login, String password, String tus){
 if (db.find(Cuenta.class, login)!=null) return false;
db.getTransaction().begin();
Cuenta c = new Cuenta(login,password,tus);
db.persist(c);
db.getTransaction().commit();
return true;
}
public boolean hacerLogin(String login, String password, String tusuario){
Cuenta c= db.find(Cuenta.class,login);
if (c==null) return false;
return (c.getPassword().equals(password)
 && (c.getTusuario().equals(tusuario)));
}
}
