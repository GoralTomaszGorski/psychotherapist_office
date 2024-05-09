package goral.psychotherapistoffice.domain.meeting.dto;

import goral.psychotherapistoffice.domain.calender.Calender;
import goral.psychotherapistoffice.domain.patient.Patient;

public class MeetingDtoDev {
    private Long id;
    private Patient patient;
    private String nick = getPatient().getNick();
    private String name = getPatient().getName();
    private String surname = getPatient().getSurname();
    private String telephone = getPatient().getTelephone();
    private Calender calender;
    private String dayof = getCalender().getDayof();
    private String time = getCalender().getTime();

    public MeetingDtoDev(Long id, Patient patient, String name, String surname, Calender calender, String time, String dayof) {
    }

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
}
