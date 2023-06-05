package gui;

import java.net.URL;
import java.util.Locale;

import javax.swing.UIManager;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;

import businessLogic.BLFacade;
import businessLogic.BLFacadeImplementation;
import configuration.ConfigXML;
import dataAccess.DataAccess;

public class ApplicationLauncher {

	public static void main(String[] args) {

		ConfigXML c=ConfigXML.getInstance();

		System.out.println(c.getLocale());

		Locale.setDefault(new Locale(c.getLocale()));

		System.out.println("Locale: "+Locale.getDefault());

		RegistroGUI a=new RegistroGUI();
		a.setVisible(true);


		try {

			BLFacade appFacadeInterface;
			UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");

			if (c.isBusinessLogicLocal()) {

				//In this option the DataAccess is created by FacadeImplementationWS
				//appFacadeInterface=new BLFacadeImplementation();

				//In this option, you can parameterize the DataAccess (e.g. a Mock DataAccess object)

				DataAccess da= new DataAccess(c.getDataBaseOpenMode().equals("initialize"));
				appFacadeInterface=new BLFacadeImplementation(da);

			}

			else { //If remote

				 String serviceName= "http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl";
//				 System.out.println("el url es este: ");
//				 System.out.println("http://"+c.getBusinessLogicNode() +":"+ c.getBusinessLogicPort()+"/ws/"+c.getBusinessLogicName()+"?wsdl");

				//URL url = new URL("http://localhost:9999/ws/ruralHouses?wsdl");
				URL url = new URL(serviceName);
				System.out.println(url);


		        //1st argument refers to wsdl document above
				//2nd argument is service name, refer to wsdl document above
		        QName qname = new QName("http://businessLogic/", "BLFacadeImplementationService");
				System.out.println(qname);

		        Service service = Service.create(url, qname);
				System.out.println(service);

		         appFacadeInterface = service.getPort(BLFacade.class);
			}
			/*if (c.getDataBaseOpenMode().equals("initialize"))
				appFacadeInterface.initializeBD();
				*/
			RegistroGUI.setBussinessLogic(appFacadeInterface);




		}catch (Exception e) {

			System.out.println("Error in ApplicationLauncher: "+e.toString());

		}
	}
}
