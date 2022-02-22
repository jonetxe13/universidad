package mailsystem;

import java.util.ArrayList;

/**
 * Class for simulating MailServer activity
 * @author PMOO teachers
 *
 */
public class Lab04Simulator {

	/**
	 * Main method
	 * @param args - No parameters required
	 */
	public static void main(String[] args) {
		testEmail();
		testMailServer();
		testMailClient();
	}

	/**
	 * Method to test the  Email class
	 */
	public static void testEmail() {
		
		//Create an Email instance using the Constructor with all fields
		Email email1 = new Email("name1", "name2", "Mail subject 1", "Mail text 1");
	
		//Create an email instance using the Constructor with all fields but "text"
		Email email2 = new Email("name2", "name1", "Mail subject 2", "");
	
		//Use all different getters on email1 and email2
		System.out.println("email1 attributes (by getters):\n");
		System.out.println("Sender: " + email1.getSender());
		System.out.println("Receiver: " + email1.getReceiver());
		System.out.println("Subject: " + email1.getSubject());
		System.out.println("Text: " + email1.getText());
	
		System.out.println("\nemail2 attributes (by getters):");
		System.out.println("Sender: " + email2.getSender());
		System.out.println("Receiver: " + email2.getReceiver());
		System.out.println("Subject: " + email2.getSubject());
		System.out.println("Text: " + email2.getText());

		//Use setter to set text in email2
		email2.setText("Mail text 2 - by setter");
	
		//Check whether the value of the text attribute has changed (by its corresponding setter)
		System.out.println("\nText: " + email2.getText());
	}
	public static void testMailServer(){
		Email user11 = new Email("user1", "user2", "sub1", "text1");
		Email user12 = new Email("user1", "user2", "sub2", "text2");
		Email user13 = new Email("user1", "user2", "sub3", "text3");
		Email user2 = new Email("user2", "user1", "sub4", "text4");
		//create an arraylist instance and add the emails to the list
		ArrayList<Email> lista = new ArrayList<Email>();
		lista.add(user11);
		lista.add(user12);
		lista.add(user13);
		lista.add(user2);
		//create a server instance and test the showAllEmails showSentEmails and showInbox methods
		MailServer servidor = new MailServer(lista, lista.size());
		/* 	servidor.showSentEmails("user1");
			servidor.showInbox("user2");
	 */		servidor.removeEmail(user11);
		servidor.showAllEmails();
	}
	public static void testMailClient() {
		MailServer servidor = new MailServer(new ArrayList<Email>(), 0);
		MailClient mailClient1 = new MailClient(servidor, "user1");
		MailClient mailClient2 = new MailClient(servidor, "user2");
		mailClient1.sendEmail("user1", "sub1", "text1");
		mailClient1.sendEmail("user1", "sub2", "text2");
		mailClient1.sendEmail("user1", "sub3", "text3");
		mailClient2.sendEmail("user2", "sub4", "text4");
		mailClient1.showMyInbox();
		mailClient1.showMySent();
	}
}
