package goral.psychotherapistoffice.web.admin;


import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingToSaveDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
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

    @GetMapping("/spotkania")
    public String meetingAdmin(@RequestParam(required = false) String keyword, Model model){
        List<MeetingDto> meetings;
        if (keyword == null){
            meetings = meetingService.findAllMeetings();
        } else {
            meetings = meetingService.findAllMeetings();
        }
        model.addAttribute("headingFA", "Terminy spotkań");
        model.addAttribute("descriptionFA", "Sprawdz terminy spotkań");
        model.addAttribute("meetings", meetings);
        return "admin/meeting-admin-view";
    }


    @GetMapping("/dadaj-rezerwacje")
    public String addMeetingForm(Model model){
        List<TherapyDto>allTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", allTherapies);
        List<PatientDto>allPatients = patientService.findAllPatients();
        model.addAttribute("patients", allPatients);
        List<CalenderDto> allFreeTherms = calenderService.findAllFreeTherms();
        model.addAttribute("therms", allFreeTherms);
        MeetingToSaveDto meetingToSaveDto = new MeetingToSaveDto();
        model.addAttribute( "meetingSave", meetingToSaveDto);
        return "admin/meeting-admin-form";

    }

    @PostMapping("/dadaj-rezerwacje")
    public String addMeeting(MeetingToSaveDto meetingSave, RedirectAttributes redirectAttributes) {
        meetingService.addMeeting(meetingSave);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Pomyślnie dokonano zapisu dla <b>%s</b> na <b>%s</b>"
                        .formatted(
                                patientService.findPatientById(meetingSave.getPatient())
                                        .map(PatientDto::getNick).orElse("Undefined"),
                                therapyService.findTherapyById(meetingSave.getTherapy())
                                        .map(TherapyDto::getKindOfTherapy).orElse("Undefined")
                                )
        );
        return "redirect:/admin";
    }

    @GetMapping("/spotkania/delete/{id}")
    public String deleteMeeting(@PathVariable(name = "id") Long id, RedirectAttributes redirectAttributes) {
        meetingService.deleteMeeting(id);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Usunięto Spotkanie "
        );
        return "redirect:/admin";
    }
}
