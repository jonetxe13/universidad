package mailsystem;

/**
 * Class for simulating MailServer activity
 * @author PMOO teachers
 *
 */
public class Lab03Simulator {

	/**
	 * Main method
	 * @param args - No parameters required
	 */
	public static void main(String[] args) {
		testEmail();
		testMailServer();
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
		Email[] lista = {user11, user12, user13, user2};
		MailServer servidor = new MailServer(lista, 5);
		servidor.showAllEmails();
	}

}
