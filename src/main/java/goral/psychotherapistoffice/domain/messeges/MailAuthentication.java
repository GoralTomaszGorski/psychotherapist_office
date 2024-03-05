package goral.psychotherapistoffice.domain.messeges;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

public class MailAuthentication extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new  PasswordAuthentication("tomasz.gorski88@gmail.com", "qruhprdwgxptvpmw");
    }
}
