package goral.psychotherapistoffice.domain.meeting;


import goral.psychotherapistoffice.domain.calender.Calender;
import goral.psychotherapistoffice.domain.calender.CalenderRepository;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingToSaveDto;
import goral.psychotherapistoffice.domain.patient.Patient;
import goral.psychotherapistoffice.domain.patient.PatientRepository;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.Therapy;
import goral.psychotherapistoffice.domain.therapy.TherapyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class MeetingService {

    public final PatientRepository patientRepository;
    public final CalenderRepository calenderRepository;
    public final MeetingRepository meetingRepository;
    public final TherapyRepository therapyRepository;
    public final PatientService patientService;

    public MeetingService(PatientRepository patientRepository, CalenderRepository calenderRepository, MeetingRepository meetingRepository, TherapyRepository therapyRepository, PatientService patientService) {
        this.patientRepository = patientRepository;
        this.calenderRepository = calenderRepository;
        this.meetingRepository = meetingRepository;
        this.therapyRepository = therapyRepository;
        this.patientService = patientService;
    }

    @Transactional
    public void addMeeting(MeetingToSaveDto meetingToSaveDto){
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


    public List<MeetingDto> findAllMeetings() {
        return  meetingRepository.findAllByCalenderIsNotNullOrderByCalender()
                .stream().map(MeetingDtoMapper::map).toList();

    }
    @Transactional
    public void addMeetingWithNewPatient(PatientDto patientDto, MeetingToSaveDto meetingToSaveDto) {
        Patient patientToSave = new Patient();
        patientToSave.setNick(patientDto.getNick());
        patientToSave.setName(patientDto.getName());
        patientToSave.setSurname(patientDto.getSurname());
        patientToSave.setTelephone(patientDto.getTelephone());
        patientToSave.setYearOfBrith(patientDto.getYearOfBrith());
        patientRepository.save(patientToSave); // Zapisz nowego pacjenta

//         Teraz możemy dodać spotkanie z nowym pacjentem
        Meeting meeting = new Meeting();
        meeting.setPatient(patientToSave); // Ustaw nowego pacjenta
        Therapy therapy = therapyRepository.findById(meetingToSaveDto.getTherapy()).orElseThrow();
        meeting.setTherapy(therapy);
        Calender calender = calenderRepository.findById(meetingToSaveDto.getCalender()).orElseThrow();

        meeting.setCalender(calender);
        calender.setFree(false);

        meetingRepository.save(meeting);
        calenderRepository.save(calender);

    }


}
