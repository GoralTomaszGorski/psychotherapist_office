package goral.psychotherapistoffice.domain.messeges;

import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import jakarta.mail.internet.MimeMessage;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;


import java.util.Properties;


@Service
public class MessageService implements MailService{

    private JavaMailSender mailSender;


    private String to;
    private String user;
    private static final String CONFIRM_MSG_TITLE = "Potwierdzneie otrzymania wiadomości";
    private static final String CONFIRM_MSG_INF = "Otrzymaliśmy Twoją wiadomość: '";
    private static final String EMAIL_TXT = " , email: ";

    Properties properties = MailConfiguration.getConfiguration();
    MailAuthentication authentication = new MailAuthentication();


//    private void sendEmail(MessageDto messageDto){
//        SimpleMailMessage message = new SimpleMailMessage();
//        message.setTo(recipient);
//        message.setFrom(user);
//        message.setText(messageDto.getText());
//        message.setText(messageDto.getId() + messageDto.getEmail());
//        mailSender.send(message);
//    }

    public String sendMessage(String to, String from, String subject, String body) {
        MimeMessage mimeMessage;
        try {
            mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage);
            mimeMessageHelper.setTo(to);
            mimeMessageHelper.setFrom(from);
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(body);
            mailSender.send(mimeMessage);
            return CONFIRM_MSG_INF + "" + subject;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}



