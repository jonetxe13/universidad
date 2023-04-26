package bussiness;
import dataAccess.F1_objectdbAccess;
import domain.Pilot;

public class Races {
	public static void main(String[] args) {
		F1_objectdbAccess f1_dataMng=new F1_objectdbAccess();
		f1_dataMng.storePilot("Fernando Alonso", "Spanish", 600);
		f1_dataMng.storePilot("Jesus Magarri", "Mexican", 405);
		f1_dataMng.storePilot("Sebastian Vettel", "German", 345);
		f1_dataMng.storePilot("Max Verstappen", "Russian", 1000);
		System.out.println(f1_dataMng.getPilotById("Jesus Magarri"));
		f1_dataMng.getAllPilots();
		f1_dataMng.getPilotByNationality("Mexican");
		Pilot jm=f1_dataMng.getPilotById("Jesus Magarri");
		f1_dataMng.deletePilot(jm);
		f1_dataMng.getAllPilots();
		f1_dataMng.close(); 
	}
}