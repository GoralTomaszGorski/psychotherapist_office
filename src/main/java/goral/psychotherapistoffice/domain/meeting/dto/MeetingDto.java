package goral.psychotherapistoffice.domain.meeting.dto;

import goral.psychotherapistoffice.domain.calender.Calender;
import goral.psychotherapistoffice.domain.patient.Patient;
import goral.psychotherapistoffice.domain.therapy.Therapy;

public class MeetingDto {
    private Patient patient;
    private Therapy therapy;
    private Calender calender;


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Therapy getTherapy() {
        return therapy;
    }

    public void setTherapy(Therapy therapy) {
        this.therapy = therapy;
    }

    public Calender getCalender() {
        return calender;
    }

    public void setCalender(Calender calender) {
        this.calender = calender;
    }

    public MeetingDto(Patient patient, Therapy therapy, Calender calender) {
        this.patient = patient;
        this.therapy = therapy;
        this.calender = calender;
    }
}
