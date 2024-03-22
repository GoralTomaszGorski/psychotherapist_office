package goral.psychotherapistoffice.domain.meeting;

import goral.psychotherapistoffice.domain.calender.Calender;
import goral.psychotherapistoffice.domain.calender.CalenderRepository;
import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.exception.TermIsBusyException;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingToSaveDto;
import goral.psychotherapistoffice.domain.patient.Patient;
import goral.psychotherapistoffice.domain.patient.PatientJpaRepository;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.Therapy;
import goral.psychotherapistoffice.domain.therapy.TherapyRepository;
import goral.psychotherapistoffice.domain.user.UserService;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;



@Service
public class MeetingService {

    public final PatientJpaRepository patientJpaRepository;
    public final CalenderRepository calenderRepository;
    public final CalenderService calenderService;
    public final MeetingRepository meetingRepository;
    public final TherapyRepository therapyRepository;
    public final PatientService patientService;
    public final UserService userService;

    public MeetingService(PatientJpaRepository patientJpaRepository, CalenderRepository calenderRepository, CalenderService calenderService, MeetingRepository meetingRepository, TherapyRepository therapyRepository, PatientService patientService, UserService userService) {
        this.patientJpaRepository = patientJpaRepository;
        this.calenderRepository = calenderRepository;
        this.calenderService = calenderService;
        this.meetingRepository = meetingRepository;
        this.therapyRepository = therapyRepository;
        this.patientService = patientService;
        this.userService = userService;
    }

    @Transactional
    public void addMeeting(MeetingToSaveDto meetingToSaveDto){
        Meeting meeting = new Meeting();
        Patient patient = patientJpaRepository.findPatientById(
                meetingToSaveDto.getPatient()).orElseThrow();
        meeting.setPatient(patient);
        Therapy therapy = therapyRepository.findById(
                meetingToSaveDto.getTherapy()).orElseThrow();
        meeting.setTherapy(therapy);
        Calender calender = calenderRepository.findCalenderByIdAndFreeIsTrue(
                meetingToSaveDto.getCalender()).orElseThrow();
        meeting.setCalender(calender);
        calender.setFree(false);
        calenderRepository.save(calender);

        meetingRepository.save(meeting);
    }

    public List<MeetingDto> findAllMeetings() {
        return  meetingRepository.findAllByCalenderIsNotNullOrderByCalender()
                .stream()
                .map(MeetingDtoMapper::map)
                .toList();

    }

    @Transactional
    public void addMeetingWithNewPatient(PatientDto patientDto, MeetingToSaveDto meetingToSaveDto) {
        Meeting meeting = new Meeting();
        Calender calender = calenderRepository.findById(
                meetingToSaveDto.getCalender()).orElseThrow();
        if (calender.isFree()) {
            meeting.setCalender(calender);
            calender.setFree(false);
        } else  {
            throw new TermIsBusyException();
        }
        calenderRepository.save(calender);

        Patient patient = patientService.addPatient(patientDto);
        meeting.setPatient(patient);

        Therapy therapy = therapyRepository.findById(
                meetingToSaveDto.getTherapy()).orElseThrow();
        meeting.setTherapy(therapy);
        meetingRepository.save(meeting);
    }

    @Transactional
    public void deleteMeeting(Long id){
        Calender calender = meetingRepository.findMeetingById(id)
                .get().getCalender();
        calender.setFree(true);
        try {
            meetingRepository.deleteMeetingById(id);
        } catch (EmptyResultDataAccessException e){
            // ignore
        }
    }

    public List<MeetingDto> findMeetingByUserMail(){
        String email = userService.getCurrentUserName();
        return meetingRepository.findMeetingByPatientEmail(email)
                .stream()
                .map(MeetingDtoMapper::map).toList();
    }

    public List<MeetingDto> findByKeyword(String keyword){
        return meetingRepository
                .findMeetingsByCalenderDayofContainsIgnoreCaseOrPatientSurnameContainsIgnoreCaseOrPatientNameContainsIgnoreCaseOrderByCalender(keyword, keyword, keyword)
                .stream()
                .map(MeetingDtoMapper::map)
                .toList();
    }

}
