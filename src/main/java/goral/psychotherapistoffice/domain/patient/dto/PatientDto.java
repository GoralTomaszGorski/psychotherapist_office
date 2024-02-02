package goral.psychotherapistoffice.domain.patient.dto;

import java.util.Set;

public class PatientDto {
    private Long id;
    private String nick;
    private String name;
    private String surname;
    private String telephone;
    private int yearOfBrith;
    private String email;
    private String password;
    private Set<String> roles;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public PatientDto(Set<String> roles) {

        this.roles = roles;
    }

    public PatientDto() {

    }


    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getYearOfBrith() {
        return yearOfBrith;
    }

    public void setYearOfBrith(int yearOfBrith) {
        this.yearOfBrith = yearOfBrith;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public PatientDto(Long id, String nick, String name, String surname, String telephone, int yearOfBrith, String email, String password, Set<String> roles) {
        this.id = id;
        this.nick = nick;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.yearOfBrith = yearOfBrith;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

}
