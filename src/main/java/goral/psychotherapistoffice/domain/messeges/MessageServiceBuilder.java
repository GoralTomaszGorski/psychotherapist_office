//package goral.psychotherapistoffice.domain.messeges;
//
//import org.springframework.mail.javamail.JavaMailSender;
//
//public class MessageServiceBuilder {
//    private JavaMailSender mailSender;
//
//    public MessageServiceBuilder setMailSender(JavaMailSender mailSender) {
//        this.mailSender = mailSender;
//        return this;
//    }
//
//    public MessageService createMessageService() {
//        return new MessageService(mailSender);
//    }
//}