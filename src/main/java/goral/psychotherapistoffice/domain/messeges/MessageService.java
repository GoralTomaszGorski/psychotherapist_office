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
}



