package goral.psychotherapistoffice.domain.messeges;

import goral.psychotherapistoffice.domain.user.UserService;

import java.net.Authenticator;
import java.net.PasswordAuthentication;

public class MailAuthentication extends Authenticator {

    @Override
    protected PasswordAuthentication getPasswordAuthentication(){
        return new PasswordAuthentication("goralek_z_gor@tlen.pl", "harnas".toCharArray());
    }
}
