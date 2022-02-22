package mailsystem;

public class MailClient {
    private MailServer server;
    private String userAddress;

    public MailClient(MailServer server, String userAddress) {
        this.server = server;
        this.userAddress = userAddress;
    }
    public void getUserAddress(String userAddress) {
        this.userAddress = userAddress;
    }
    public void sendEmail(String receiver, String subject, String text) {
        server.storeEmail(new Email(userAddress, receiver, subject, text));
    }
    public void showMyInbox() {
        server.showInbox(userAddress);
    }
    public void showMySent(){
        server.showSentEmails(userAddress);
    }
}
