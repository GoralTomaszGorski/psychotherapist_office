package goral.psychotherapistoffice.domain.meeting;

import goral.psychotherapistoffice.domain.calender.Calender;
import goral.psychotherapistoffice.domain.patient.Patient;
import goral.psychotherapistoffice.domain.therapy.Therapy;
import jakarta.persistence.*;

@Entity
@Table(name = "meeting")

public class Meeting {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "patient_id", referencedColumnName = "id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "therapy_id", referencedColumnName = "id")
    private Therapy therapy;
    @OneToOne
    @JoinColumn(name = "calender_id", referencedColumnName = "id")
    private Calender calender;

    public Meeting() {
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

    public Meeting(Long id, Patient patient, Therapy therapy, Calender calender) {
        this.id = id;
        this.patient = patient;
        this.therapy = therapy;
        this.calender = calender;
    }
}
