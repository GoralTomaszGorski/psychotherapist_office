package goral.psychotherapistoffice.domain.meeting.dto;

public class MeetingVisitorSaveDto {
    private Long patient;
    private Long therapy;
    private Long calender;

    public Long getPatient() {
        return patient;
    }

    public void setPatient(Long patient) {
        this.patient = patient;
    }

    public Long getTherapy() {
        return therapy;
    }

    public void setTherapy(Long therapy) {
        this.therapy = therapy;
    }

    public Long getCalender() {
        return calender;
    }

    public void setCalender(Long calender) {
        this.calender = calender;
    }
}
