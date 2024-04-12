package goral.psychotherapistoffice.domain.user.Dto;

import java.util.Set;

public class UserCredentialsDto {
    private String email;
    private String password;
    private Set<String> roles;

    public UserCredentialsDto(String email, String password, Set<String> roles) {
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public UserCredentialsDto() {

    }

    public void setEmail(String email) {
        this.email = email;
    }
    public void setPassword(String password) {
        this.password = password;
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

}