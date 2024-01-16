package goral.psychotherapistoffice.web.visitor;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingVisitorSaveDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class VisitorController {


    private final PatientService patientService;
    private final CalenderService calenderService;
    private final MeetingService meetingService;
    private final TherapyService therapyService;

    public VisitorController(PatientService patientService, CalenderService calenderService, MeetingService meetingService, TherapyService therapyService) {
        this.patientService = patientService;
        this.calenderService = calenderService;
        this.meetingService = meetingService;
        this.therapyService = therapyService;
    }


    //stała z UserManagementController
    public static final String NOTIFICATION_ATTRIBUTE = "visitorNotification";
    public static final String NOTIFICATION_ATTRIBUTE2 = "visitorNotification2";

    @GetMapping("/visitor")
    public String getAdminPanel(){
        return "visitor/visitor";
    }



    @GetMapping("/termin/{id}")
    public String addMeetingByVisitor(@PathVariable long id, Model model){
        //1. wybranie z kalendarza

        CalenderDto calender = calenderService.findCalenderByIdIfFreeIsTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("calender", calender);

        PatientDto patientDto = new PatientDto();
        model.addAttribute("patientDto", patientDto);

        List<TherapyDto> allTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", allTherapies);
        //3. ustawienie meetingu
        MeetingVisitorSaveDto meetingVisitorSaveDto = new MeetingVisitorSaveDto();
        model.addAttribute( "meetingSave", meetingVisitorSaveDto);

        return "visitor/meeting-form";
    }

    @PostMapping("/termin/{id}")
    public String addPatientAndMeeting(@ModelAttribute("patientDto") PatientDto patientDto, @ModelAttribute("meetingSave") MeetingVisitorSaveDto meetingSave, RedirectAttributes redirectAttributes){
       //1 zmiana

        patientService.addPatient(patientDto);

        meetingSave.setPatient(patientDto.getId());

        redirectAttributes.addFlashAttribute(
                VisitorController.NOTIFICATION_ATTRIBUTE,
                "Pacjęt %s %s pseudonim %s został zapisany "
                        .formatted(
                                patientDto.getName(),
                                patientDto.getSurname(),
                                patientDto.getNick())
                        );

        meetingService.addMeeting(meetingSave);
        redirectAttributes.addFlashAttribute(
                VisitorController.NOTIFICATION_ATTRIBUTE2,
                "na terepię %s %s "
                        .formatted(
                                meetingSave.getTherapy(),
                                meetingSave.getCalender()
                        )
        );
        return "redirect:/visitor";
    }


}
