package goral.psychotherapistoffice.domain.messeges;

import java.util.Properties;

public class MailConfiguration {
    public static Properties getConfiguration(){
        Properties props = new Properties();
        props.put("mail.smtp.auth", true);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "poczta.tlen.pl");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.ssl.trust", "smtp.mailtrap.io");
        return props;
    };

}
