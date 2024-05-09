package goral.psychotherapistoffice.domain.messeges;
import goral.psychotherapistoffice.domain.exception.MailSenderException;
import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import goral.psychotherapistoffice.domain.user.Dto.UserCredentialsDto;
import jakarta.mail.*;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import org.springframework.stereotype.Service;
import java.util.Properties;

@Service
public class MessageService{

    Properties properties = MailConfiguration.getConfiguration();
    MailAuthentication authenticator = new MailAuthentication();
    Session session = Session.getInstance(properties, authenticator);

    public void sendMailByMessagebox(MessageDto messageDto) {
        try {
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("ewagorska88@wp.pl"));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress("gabinet.ewa.gorska@gmail.com"));
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

    public String sendMailToResetPassword(UserCredentialsDto userCredentialsDto, String resetLink) throws MailSenderException{

        try {
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(userCredentialsDto.getEmail()));
            message.setText(  "Dzień dobry \n\n" + "Kliknij link poniżej żeby zresetować hasło: \n\n"  + resetLink+ "\n\n"
                    + "z poważaniem \n" + "Gabinet Psychoterapeutyczny \n Ewa Górska");
            message.setSubject("Reset hasła -  Gabinet Psychoterapeutyczny Ewa Górska");
            Transport.send(message);
            return "success";
        }
        catch (Throwable throwable ) {
            return "error";
        }
    }

}



