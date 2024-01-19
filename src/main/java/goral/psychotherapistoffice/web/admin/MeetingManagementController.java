package goral.psychotherapistoffice.web.admin;


import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingVisitorSaveDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import goral.psychotherapistoffice.web.visitor.VisitorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class MeetingManagementController {

    private final MeetingService meetingService;
    private final PatientService patientService;
    private final CalenderService calenderService;
    private final TherapyService therapyService;

    public MeetingManagementController(MeetingService meetingService, PatientService patientService, CalenderService calenderService, TherapyService therapyService) {
        this.meetingService = meetingService;
        this.patientService = patientService;
        this.calenderService = calenderService;
        this.therapyService = therapyService;
    }

    @GetMapping("admin/spotkania")
    public String meetingAdmin(Model model){
        List<MeetingDto> meetingsThermsForAdmin = meetingService.findAllMeetings();
        model.addAttribute("headingFA", "Terminy spotkań");
        model.addAttribute("descriptionFA", "Sprawdz terminy spotkań");
        model.addAttribute("meetingThermsOccupiedForAdmin", meetingsThermsForAdmin);
        return "admin/meeting-admin-view";
    }

    @GetMapping("/admin/dadaj-rezerwacje")
    public String addMeetingForm(Model model){
        List<TherapyDto>allTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", allTherapies);
        List<PatientDto>allPatients = patientService.findAllPatients();
        model.addAttribute("patients", allPatients);
        List<CalenderDto> allFreeTherms = calenderService.findAllFreeTherms();
        model.addAttribute("therms", allFreeTherms);
        MeetingVisitorSaveDto meetingVisitorSaveDto = new MeetingVisitorSaveDto();
        model.addAttribute( "meetingSave", meetingVisitorSaveDto);
        return "admin/meeting-admin-form";

    }

    @PostMapping("/admin/dadaj-rezerwacje")
    public String addMeeting(MeetingVisitorSaveDto meetingSave, RedirectAttributes redirectAttributes) {
        meetingService.addMeeting(meetingSave);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Pomyślnie dokonano zapisu dla <b>%s</b> na <b>%s</b>"
                        .formatted(
                                patientService.findPatientById(meetingSave.getPatient()).map(PatientDto::getNick).orElse("Undefined"),
                                therapyService.findTherapyById(meetingSave.getTherapy()).map(TherapyDto::getKindOfTherapy).orElse("Undefined")
                                )
        );
        return "redirect:/admin";
    }
}
