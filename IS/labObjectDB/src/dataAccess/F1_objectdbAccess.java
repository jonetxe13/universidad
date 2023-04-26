package dataAccess;
import java.util.List;

import javax.persistence.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import domain.Pilot;

public class F1_objectdbAccess {
	
	private EntityManager db;
	private EntityManagerFactory emf;
	String fileName = "formula1.temp" + ";drop";

	public F1_objectdbAccess() {
	 emf = Persistence.createEntityManagerFactory("objectdb:"+fileName);
	 db = emf.createEntityManager();
	 System.out.println("Base de datos abierta");
	} 
	 
	public void close(){
	 db.close();
	 System.out.println("Base de datos cerrada");
	}
	public void storePilot(String name,String nac, int points) {
		db.getTransaction().begin();
		Pilot pilot = new Pilot(name, nac, points);
		db.persist(pilot);
		db.getTransaction().commit();
		System.out.println("Insertado: " + pilot);
	}
	public Pilot getPilotById(String name) {
		return db.find(Pilot.class,name);
	}
	public void getAllPilots() { 
		TypedQuery<Pilot> query = db.createQuery("SELECT p FROM Pilot p",Pilot.class); 
		List<Pilot> pilots = query.getResultList();
		System.out.println("Contenido de la base de datos:");
		for (Pilot p:pilots) System.out.println(p.toString());
	}
	public void getPilotByNationality(String nac) {
		TypedQuery<Pilot> query = 
		 db.createQuery("SELECT p FROM Pilot p WHERE p.nationality='"+nac+"'",Pilot.class); 
		List<Pilot> pilots = query.getResultList();
		System.out.println("Contenido de la base de datos");
		for (Pilot p:pilots) System.out.println(p.toString());
	}
	public void deletePilot(Pilot p) {
		db.getTransaction().begin();
		db.remove(p);
		db.getTransaction().commit();
	}
	public void deletePilotByName(String name) {
		db.getTransaction().begin();
		Query query = db.createQuery("DELETE FROM Pilot p WHERE p.name='"+name+"'"); 
		int deletedPilots = query.executeUpdate();
		System.out.println("Pilotos borrados: " + deletedPilots);
		db.getTransaction().commit(); 
	}
	public void updatePilotByNameAddingPoints(String name, int points) {
		Pilot pilot=getPilotById(name);
		if (pilot==null) 
		 System.out.println(name + " no está en la base de datos"); 
		else {
			db.getTransaction().begin();
			pilot.addPoints(points);
			db.getTransaction().commit();
			System.out.println(pilot + " ha sido actualizado");
		}
	}
}