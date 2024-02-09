package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingToSaveDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Controller
public class UserAddMeetingController {


    private final PatientService patientService;
    private final CalenderService calenderService;
    private final MeetingService meetingService;
    private final TherapyService therapyService;

    private long calenderIdLocal ;

    public UserAddMeetingController(PatientService patientService, CalenderService calenderService, MeetingService meetingService, TherapyService therapyService) {
        this.patientService = patientService;
        this.calenderService = calenderService;
        this.meetingService = meetingService;
        this.therapyService = therapyService;
    }

    @GetMapping("/termin/{calenderId}")
    public String addMeetingByUserForm(@PathVariable long calenderId, Model model) {
        //1. wybranie z kalendarza
        CalenderDto calender = calenderService.findCalenderById(calenderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("calender", calender);


        List<TherapyDto> allTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", allTherapies);

//        List<PatientDto> allPatients = patientService.findAllPatients();
//        model.addAttribute("patients", allPatients);

        PatientDto patientDto = new PatientDto();
        model.addAttribute("patientDto", patientDto);

        MeetingToSaveDto meetingToSaveDto = new MeetingToSaveDto();
        model.addAttribute("meetingSave", meetingToSaveDto);
        calenderIdLocal = calenderId;
        return "user/meeting-form";
    }

    @PostMapping("/termin/add")
    public String addMeetingWithNewPatient(@ModelAttribute MeetingToSaveDto meetingToSaveDto, PatientDto patientDto, Model model){
        model.addAttribute("meetingSave", meetingToSaveDto);
        meetingToSaveDto.setCalender(calenderIdLocal);
        meetingService.addMeetingWithNewPatient(patientDto, meetingToSaveDto);
        return "redirect:/";
    };

}
