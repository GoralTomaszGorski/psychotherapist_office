package goral.psychotherapistoffice.domain.meeting;


import goral.psychotherapistoffice.domain.calender.Calender;
import goral.psychotherapistoffice.domain.calender.CalenderRepository;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingVisitorSaveDto;
import goral.psychotherapistoffice.domain.patient.Patient;
import goral.psychotherapistoffice.domain.patient.PatientRepository;
import goral.psychotherapistoffice.domain.therapy.Therapy;
import goral.psychotherapistoffice.domain.therapy.TherapyRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeetingService {

    public final PatientRepository patientRepository;
    public final CalenderRepository calenderRepository;
    public final MeetingRepository meetingRepository;
    public final TherapyRepository therapyRepository;

    public MeetingService(PatientRepository patientRepository, CalenderRepository calenderRepository, MeetingRepository meetingRepository, TherapyRepository therapyRepository) {
        this.patientRepository = patientRepository;
        this.calenderRepository = calenderRepository;
        this.meetingRepository = meetingRepository;
        this.therapyRepository = therapyRepository;
    }

    public List<MeetingDto>findAllMeetings(){
        return meetingRepository.findAllByCalenderIsNotNullOrderByCalender()
                .stream()
                .map(MeetingDtoMapper::map)
                .toList();
    }


    public void addMeeting(MeetingVisitorSaveDto meetingToSaveDto){
        Meeting meeting = new Meeting();

        Patient patient = patientRepository.findPatientById(meetingToSaveDto.getPatient()).orElseThrow();
        meeting.setPatient(patient);
        Therapy therapy = therapyRepository.findById(meetingToSaveDto.getTherapy()).orElseThrow();
        meeting.setTherapy(therapy);
        Calender calender = calenderRepository.findById(meetingToSaveDto.getCalender()).orElseThrow();
        meeting.setCalender(calender);
        calender.setFree(false);
        meetingRepository.save(meeting);
        calenderRepository.save(calender);
    }


}
