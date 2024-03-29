package goral.psychotherapistoffice.domain.patient.dto;

import java.time.Year;

public class PatientDto {
    private Long id;
    private String nick;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private Year yearOfBrith;

    public PatientDto() {

    }

    public PatientDto(Long id, String nick, String name, String surname, String email, String telephone, Year yearOfBrith) {
        this.id = id;
        this.nick = nick;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
        this.yearOfBrith = yearOfBrith;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Year getYearOfBrith() {
        return yearOfBrith;
    }

    public void setYearOfBrith(Year yearOfBrith) {
        this.yearOfBrith = yearOfBrith;
    }
}
