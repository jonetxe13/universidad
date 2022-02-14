package mailsystem;

public class Email {
   private String sender;
   private String receiver;
   private String subject;
   private String text = "";

   public Email(String sender1, String receiver1, String subject1, String text1){
    sender = sender1;
    receiver = receiver1;
    subject = subject1;
    setText(text1);
   }

   public String getText() {
      return text;
   }

   public void setText(String text) {
      this.text = text;
   }

   public String getReceiver() {
      return receiver;
   }
   public String getSender() {
      return sender;
   }

   public String getSubject() {
      return subject;
   }   
   public void showEmail(){
      System.out.println("Sender:" + sender);
      System.out.println("Receiver" + receiver);
      System.out.println("Subject:" + subject);
      System.out.println("Text:" + text);
   }
}