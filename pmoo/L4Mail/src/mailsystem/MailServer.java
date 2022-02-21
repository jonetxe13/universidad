package mailsystem;

import java.util.ArrayList;

/**
 * Class for managing mail servers
 * @author PMOO teachers
 *
 */
public class MailServer {
	//Attribute for storing emails
	private ArrayList<Email> emailList = new ArrayList<Email>();

	//Attribute for Maximum capacity
	private final int MAXIMUM_CAPACITY = 100;

	//Attribute for number of stored emails
	private int numEmails;
	/**
	 * MailServer Constructor
	 */
	public MailServer(ArrayList<Email> emailList1, int n) {
		emailList = emailList1;
		numEmails = n;
	}
	
	/**
	 * Method that stores an Email at the end of the list
	 * @param pMail Email to be stored
	 */
	public void storeEmail(Email pMail) {
			for(int i = 0; i < MAXIMUM_CAPACITY; i++){
				if(emailList.get(i) == null) {
					emailList.set(i, pMail);
					numEmails += 1;
				}
				if(emailList.get(i+2) == null & i <= MAXIMUM_CAPACITY-2) break;
			}
	}
	public void showAllEmails() {
		if (emailList.size() == 0) {
			System.out.println("esta vacia"); 
		}
		else{
			for (int i = 0; i < MAXIMUM_CAPACITY; i++){
				if (numEmails-1 <= i) break;
				emailList.get(i).showEmail();
				System.out.println("\n");
			}
		}
	}
	public void resetServer(){
		emailList.clear();
	}
	public void showSentEmails(String username){
		for(int i = 0; i<emailList.size(); i++){
			if (emailList.get(i).getSender() == username) emailList.get(i).showEmail();
		}
	}
	public void showInbox(String username){
		for(int i = 0; i<emailList.size(); i++){
			if (emailList.get(i).getReceiver() == username) emailList.get(i).showEmail();
		}
	}
	public void removeEmail(Email email){
		for (int i = 0; i < emailList.size(); i++){
			if (emailList.get(i) == email) emailList.remove(i);
		}
	}

	public MailServer(ArrayList<Email> emailList) {
		this.emailList = emailList;
	}
}
