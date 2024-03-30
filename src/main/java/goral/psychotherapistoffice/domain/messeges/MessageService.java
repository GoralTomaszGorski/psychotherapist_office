package goral.psychotherapistoffice.domain.messeges;
import goral.psychotherapistoffice.domain.exception.MailSenderException;
import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class MessageService{

    public void sendMail(MessageDto messageDto) {
        //logic
        //smtp properties
        Properties properties = MailConfiguration.getConfiguration();
        MailAuthentication authenticator = new MailAuthentication();
        //session
        Session session = Session.getInstance(properties, authenticator);
        try {
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    authenticator.getPasswordAuthentication().getUserName()));
            message.setFrom(new InternetAddress(messageDto.getFrom()));
            message.setText(messageDto.getBody());
            message.setSubject(
                    messageDto.getSubject()+", od: " +messageDto.getFrom()+" tel.: "
                            +messageDto.getPhone());
            message.setHeader((messageDto.getPhone()), (messageDto.getFrom()));
            Transport.send(message);
        }
        catch (Throwable e) {
            throw new MailSenderException();
        }
    }

    public void sendToken(String resetLink) {
        //logic
        //smtp properties
        Properties properties = MailConfiguration.getConfiguration();
        MailAuthentication authenticator = new MailAuthentication();
        //session
        Session session = Session.getInstance(properties, authenticator);
        try {
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(
                    authenticator.getPasswordAuthentication().getUserName()));


            message.setText("Hello \n\n" + "Please click on this link to Reset your Password :" + resetLink + ". \n\n"
                    + "Regards \n" + "Gabinet psychoterapetyczny Ewa Górsk");
            message.setSubject("Gabinet psychoterapetyczny Ewa Górska reset hasła do API");
            Transport.send(message);
        }
        catch (Throwable e) {
            throw new MailSenderException();
        }
    }
}



