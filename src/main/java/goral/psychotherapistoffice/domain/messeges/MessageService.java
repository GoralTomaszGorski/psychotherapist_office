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

    public boolean sendMail(MessageDto messageDto) throws MessagingException {
        boolean flag = false;
        Properties properties = MailConfiguration.getConfiguration();
//        MailAuthentication authentication = new MailAuthentication();

        String username = "goralek_z_gor@o2.pl";
        String password = "harnas";
        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });
        try {
            Message message = new MimeMessage(session);
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(messageDto.getRecipient()));
            message.setFrom(new InternetAddress(messageDto.getFrom()));
            message.setText(messageDto.getBody());
            message.setSubject(messageDto.getSubject());
            Transport.send(message);
            flag = true;
        } catch (Exception e) {
            throw new MailSenderException();
        }
        return flag;
    }
}



