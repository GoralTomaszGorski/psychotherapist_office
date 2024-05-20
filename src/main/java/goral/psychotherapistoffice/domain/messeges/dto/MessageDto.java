package goral.psychotherapistoffice.domain.messeges.dto;

public class MessageDto {

    private String phone;
    private String recipient;
    private String sender;
    private String subject;
    private String body;

    public MessageDto(String phone, String sender, String subject, String recipient, String body) {
        this.phone = phone;
        this.recipient = recipient;
        this.sender = sender;
        this.subject = subject;
        this.body = body;
    }

    public MessageDto() {

    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}



