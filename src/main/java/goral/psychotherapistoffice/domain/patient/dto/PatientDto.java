package goral.psychotherapistoffice.domain.patient.dto;

import java.sql.Date;
import java.time.ZonedDateTime;

public class PatientDto {
    private Long id;
    private String nick;
    private String name;
    private String surname;
    private String email;
    private String telephone;
    private int yearOfBrith;
    private ZonedDateTime joinDate;
    private String  information;
    private boolean approval;

    public PatientDto(ZonedDateTime joinDate) {
        this.joinDate = joinDate;
    }

    public PatientDto(Long id, String nick, String name, String surname, String email, String telephone, int yearOfBrith, ZonedDateTime joinDate, String  information, boolean approval) {
        this.id = id;
        this.nick = nick;
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.telephone = telephone;
        this.yearOfBrith = yearOfBrith;
        this.joinDate = joinDate;
        this.information = information;
        this.approval = approval;
    }

    public PatientDto() {

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

    public int getYearOfBrith() {
        return yearOfBrith;
    }

    public void setYearOfBrith(int yearOfBrith) {
        this.yearOfBrith = yearOfBrith;
    }

    public boolean isApproval() {
        return approval;
    }

    public void setApproval(boolean approval) {
        this.approval = approval;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public ZonedDateTime getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(ZonedDateTime joinDate) {
        this.joinDate = joinDate;
    }
}
