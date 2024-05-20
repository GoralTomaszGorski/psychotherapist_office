package goral.psychotherapistoffice.domain.messeges;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

public class MailAuthentication extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {
        return new  PasswordAuthentication("ewagorska88@wp.pl", "GKU2EFMARMXAXECS");
    }
}
