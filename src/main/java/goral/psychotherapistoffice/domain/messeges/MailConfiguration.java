package goral.psychotherapistoffice.domain.messeges;


import java.util.Properties;

public class MailConfiguration {
    public static Properties getConfiguration() {
        Properties props = new Properties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "smtp.wp.pl");
        props.put("mail.smtp.port", "465");
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.debug", "true");
        return props;
    }
}
