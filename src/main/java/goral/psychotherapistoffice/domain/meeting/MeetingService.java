package goral.psychotherapistoffice.domain.meeting;


import goral.psychotherapistoffice.domain.calender.Calender;
import goral.psychotherapistoffice.domain.calender.CalenderRepository;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingToSaveDto;
import goral.psychotherapistoffice.domain.patient.Patient;
import goral.psychotherapistoffice.domain.patient.PatientJpaRepository;
import goral.psychotherapistoffice.domain.patient.PatientRepository;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.Therapy;
import goral.psychotherapistoffice.domain.therapy.TherapyRepository;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class MeetingService {



    public final PatientRepository patientRepository;
    public final PatientJpaRepository patientJpaRepository;
    public final CalenderRepository calenderRepository;
    public final MeetingRepository meetingRepository;
    public final TherapyRepository therapyRepository;
    public final PatientService patientService;

    public MeetingService(PatientRepository patientRepository, PatientJpaRepository patientJpaRepository, CalenderRepository calenderRepository, MeetingRepository meetingRepository, TherapyRepository therapyRepository, PatientService patientService) {
        this.patientRepository = patientRepository;
        this.patientJpaRepository = patientJpaRepository;
        this.calenderRepository = calenderRepository;
        this.meetingRepository = meetingRepository;
        this.therapyRepository = therapyRepository;
        this.patientService = patientService;
    }

    @Transactional
    public void addMeeting(MeetingToSaveDto meetingToSaveDto){
        Meeting meeting = new Meeting();
        Patient patient = patientJpaRepository.findPatientById(meetingToSaveDto.getPatient()).orElseThrow();
        meeting.setPatient(patient);
        Therapy therapy = therapyRepository.findById(meetingToSaveDto.getTherapy()).orElseThrow();
        meeting.setTherapy(therapy);
        Calender calender = calenderRepository.findCalenderByIdAndFreeIsTrue(meetingToSaveDto.getCalender()).orElseThrow();
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
        patientService.addPatient(patientDto);
//         Teraz możemy dodać spotkanie z nowym pacjentem
        Meeting meeting = new Meeting();
        Calender calender = calenderRepository.findById(meetingToSaveDto.getCalender()).orElseThrow();
        if (calender.isFree()) {
            meeting.setCalender(calender);
            calender.setFree(false);
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
        meeting.setPatient(meeting.getPatient()); // Ustaw nowego pacjenta
        Therapy therapy = therapyRepository.findById(meetingToSaveDto.getTherapy()).orElseThrow();
        meeting.setTherapy(therapy);
        meetingRepository.save(meeting);
        calenderRepository.save(calender);
    }

    public  Optional<MeetingDto>findMeetingId(long id){
        return meetingRepository.findMeetingById(id)
                .map(MeetingDtoMapper::map);
    }


    @Transactional
    public void deleteMeeting(Long id){
        try {
            meetingRepository.deleteMeetingById(id);
        } catch (EmptyResultDataAccessException e){
            // ignore

        }
    }
}
