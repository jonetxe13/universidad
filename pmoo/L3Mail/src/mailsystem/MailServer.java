package mailsystem;

/**
 * Class for managing mail servers
 * @author PMOO teachers
 *
 */
public class MailServer {
	
	//Attribute for storing emails
	private Email[] emailList;

	//Attribute for Maximum capacity
	private final int MAXIMUM_CAPACITY = 100;

	//Attribute for number of stored emails
	private int numEmails;

	/**
	 * MailServer Constructor
	 */
	public MailServer(Email[] list, int n) {
		emailList = list;
		numEmails = n;
	}
	
	/**
	 * Method that stores an Email at the end of the list
	 * @param pMail Email to be stored
	 */
	public void storeEmail(Email pMail) {
			for(int i = 0; i < MAXIMUM_CAPACITY; i++){
				if(emailList[i] == null) {
					emailList[i] = pMail;
					numEmails += 1;
				}
				if(emailList[i+2] == null & i <= MAXIMUM_CAPACITY-2) break;
			}
	}
	public void showAllEmails() {
		for (int i = 0; i < MAXIMUM_CAPACITY; i++){
			if (numEmails-1 <= i) break;
			emailList[i].showEmail();
			System.out.println("\n");
		}
	}
	
	
	
}
