package goral.psychotherapistoffice.domain.meeting.dto;

import goral.psychotherapistoffice.domain.calender.Calender;
import goral.psychotherapistoffice.domain.patient.Patient;
import goral.psychotherapistoffice.domain.therapy.Therapy;

public class MeetingDto {
    private Long id;
    private Patient patient;
    private String nick;
    private String name;
    private String surname;
    private String telephone;
    private Therapy therapy;
    private String kindOfTherapy;
    private Calender calender;
    private String dayof;
    private String time;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
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

    public Therapy getTherapy() {
        return therapy;
    }

    public void setTherapy(Therapy therapy) {
        this.therapy = therapy;
    }

    public String getKindOfTherapy() {
        return kindOfTherapy;
    }

    public void setKindOfTherapy(String kindOfTherapy) {
        this.kindOfTherapy = kindOfTherapy;
    }

    public Calender getCalender() {
        return calender;
    }

    public void setCalender(Calender calender) {
        this.calender = calender;
    }

    public String getDayof() {
        return dayof;
    }

    public void setDayof(String dayof) {
        this.dayof = dayof;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public MeetingDto(Long id, String nick, String name, String surname, String telephone, String kindOfTherapy, String dayof, String time) {
        this.id = id;
        this.patient = patient;
        this.nick = nick;
        this.name = name;
        this.surname = surname;
        this.telephone = telephone;
        this.therapy = therapy;
        this.kindOfTherapy = kindOfTherapy;
        this.calender = calender;
        this.dayof = dayof;
        this.time = time;
    }
}
