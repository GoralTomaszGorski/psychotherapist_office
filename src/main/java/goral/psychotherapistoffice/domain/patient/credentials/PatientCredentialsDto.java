package goral.psychotherapistoffice.domain.patient.credentials;

import java.util.Set;

public class PatientCredentialsDto {
    private final String nick;
    private final String email;
    private final String password;
    private final Set<String> roles;


    public String getNick() {
        return nick;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public Set<String> getRoles() {
        return roles;
    }

    public PatientCredentialsDto(String nick, String email, String password, Set<String> roles) {
        this.nick = nick;
        this.email = email;
        this.password = password;
        this.roles = roles;

    }
}
