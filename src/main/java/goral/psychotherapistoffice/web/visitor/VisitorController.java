package goral.psychotherapistoffice.web.visitor;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingVisitorSaveDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.Therapy;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

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

    @GetMapping("/visitor")
    public String getAdminPanel(){
        return "visitor/visitor";
    }


    @GetMapping("/visitor/dadaj-rezerwacje")
    public String addMeetingForm(Model model){


        List<TherapyDto>allTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", allTherapies);
        List<PatientDto>allPatients = patientService.findAllPatients();
        model.addAttribute("patients", allPatients);
        List<CalenderDto> allFreeTherms = calenderService.findAllFreeTherms();
        model.addAttribute("therms", allFreeTherms);
        MeetingVisitorSaveDto meetingVisitorSaveDto = new MeetingVisitorSaveDto();
        model.addAttribute( "meetingSave", meetingVisitorSaveDto);
        return "visitor/meeting-form";

    }

    @PostMapping("/visitor/dadaj-rezerwacje")
    public String addMeeting(MeetingVisitorSaveDto meetingSave, RedirectAttributes redirectAttributes) {
        meetingService.addMeeting(meetingSave);

        redirectAttributes.addFlashAttribute(
                VisitorController.NOTIFICATION_ATTRIBUTE,
                "Pomyłśnie dokonano zapisu %s %s"
                        .formatted(
                                therapyService.findTherapyById(meetingSave.getTherapy()).map(TherapyDto::getKindOfTherapy).orElse("asdfadsf"),
                                patientService.findPatientById(meetingSave.getPatient()).map(PatientDto::getNick).orElse("Undefined")
                        )
        );
        return "redirect:/visitor";
    }
}
