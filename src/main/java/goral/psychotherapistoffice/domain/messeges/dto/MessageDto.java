package goral.psychotherapistoffice.domain.messeges.dto;

public class MessageDto {

        private Long id;
        private String email;
        private String text;
        private boolean confirmation;

    public MessageDto(Long id, String email, String text, boolean confirmation) {
        this.id = id;
        this.email = email;
        this.text = text;
        this.confirmation = confirmation;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isConfirmation() {
        return confirmation;
    }

    public void setConfirmation(boolean confirmation) {
        this.confirmation = confirmation;
    }
}
