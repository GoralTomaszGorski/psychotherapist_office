package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.exception.TermIsBusyException;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingToSaveDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import goral.psychotherapistoffice.domain.user.UserService;
import goral.psychotherapistoffice.web.HomeController;
import goral.psychotherapistoffice.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
public class UserAddMeetingController {

    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    private final CalenderService calenderService;
    private final MeetingService meetingService;
    private final TherapyService therapyService;
    private final UserService userService;
    private final PatientService patientService;

    private long calenderIdLocal ;
    public UserAddMeetingController(CalenderService calenderService, MeetingService meetingService, TherapyService therapyService, UserService userService, PatientService patientService) {
        this.calenderService = calenderService;
        this.meetingService = meetingService;
        this.therapyService = therapyService;
        this.userService = userService;
        this.patientService = patientService;
    }

    @GetMapping("/termin/{calenderId}")
    public String addMeetingByUserForm(
            @PathVariable long calenderId,
            Model model) throws TermIsBusyException{
        model.addAttribute("heading", "Podaj swoje dane, aby umówić wizytę");
        model.addAttribute("description", "Twoje dane będzie widział jedynie terapeuta. Ogólnodostępne będzie jednynie NICK. ");
        model.addAttribute("additinal", "Jeśli nie chcesz podawać nicku zostanie wygenerowny automatycznie 3 ostatnie cyfry z nr tel, 2 pierwsze litery nazwiska i pierwsza litera imienia.");
        //1. wybranie z kalendarza
        CalenderDto calender = calenderService.findFreeCalenderById(calenderId)
                .orElseThrow(TermIsBusyException::new);
        model.addAttribute("calender", calender);
        calenderIdLocal = calenderId;

        List<TherapyDto> allTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", allTherapies);

        PatientDto patientToSave = new PatientDto();
        model.addAttribute("patientToSave", patientToSave);

        MeetingToSaveDto meetingToSaveDto = new MeetingToSaveDto();
        model.addAttribute("meetingSave", meetingToSaveDto);
        return "user/meeting-form";
    }

    @PostMapping("/termin/add")
    public String addMeetingWithNewPatient(
            @ModelAttribute MeetingToSaveDto meetingToSaveDto,
            PatientDto patientToSave,
            Model model,
            RedirectAttributes redirectAttributes) {
        model.addAttribute("meetingSave", meetingToSaveDto);
        meetingToSaveDto.setCalender(calenderIdLocal);
        meetingService.addMeetingWithNewPatient(patientToSave, meetingToSaveDto);
        redirectAttributes.addFlashAttribute(
                HomeController.NOTIFICATION_ATTRIBUTE,
                "Pacjent <b>%s %s </b> pseudonim <b>%s</b> został zapisany na <b>%s</b> na <b>%s</b>"
                        .formatted(
                                patientToSave.getName(),
                                patientToSave.getSurname(),
                                patientToSave.getNick(),
                                calenderService.findCalenderById(calenderIdLocal)
                                        .map(CalenderDto::getDayof).orElse("Undefined"),
                                therapyService.findTherapyById(meetingToSaveDto.getTherapy())
                                        .map(TherapyDto::getKindOfTherapy).orElse("Undefined")
                        )
                );
        return "redirect:/";
    }

    @GetMapping("user/dadaj-rezerwacje")
    public String addMeetingForm(Model model){
        model.addAttribute("heading", "Podaj swoje dane, aby umówić wizytę");
        model.addAttribute("description", "Twoje dane będzie widział jedynie terapeuta. Ogólnodostępne będzie jednynie NICK. ");

        List<TherapyDto>allTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", allTherapies);

        String email = userService.getCurrentUserName();
        List<PatientDto> patient = patientService.findPatientByEmail(email);
                model.addAttribute("patients", patient);

        List<CalenderDto> allFreeTherms = calenderService.findAllFreeTherms();
        model.addAttribute("therms", allFreeTherms);

        MeetingToSaveDto meetingToSaveDto = new MeetingToSaveDto();
        model.addAttribute( "meetingSave", meetingToSaveDto);
        return "user/meeting-user-form";
    }

    @PostMapping("user/dadaj-rezerwacje")
    public String addMeeting(MeetingToSaveDto meetingSave, RedirectAttributes redirectAttributes) {
        meetingService.addMeeting(meetingSave);
        redirectAttributes.addFlashAttribute(
                UserController.NOTIFICATION_ATTRIBUTE,
                "Pomyślnie dokonano zapisu dla <b>%s</b> na <b>%s</b> <b>%s</b>"
                        .formatted(
                                patientService.findPatientById(meetingSave.getPatient())
                                        .map(PatientDto::getNick).orElse("Undefined"),
                                therapyService.findTherapyById(meetingSave.getTherapy())
                                        .map(TherapyDto::getKindOfTherapy).orElse("Undefined"),
                                calenderService.findCalenderById(meetingSave.getCalender())
                                        .map(CalenderDto::getDayof).orElse("Undefined"),
                                calenderService.findCalenderById(meetingSave.getCalender())
                                        .map(CalenderDto::getTime).orElse("Undefined")
                        )
        );
        return "redirect:/user";
    }

}
