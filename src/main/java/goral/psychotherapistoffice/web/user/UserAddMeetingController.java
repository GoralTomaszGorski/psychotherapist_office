package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingToSaveDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import goral.psychotherapistoffice.domain.user.UserService;
import goral.psychotherapistoffice.web.HomeController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserAddMeetingController {

    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    private final UserService userService;
    private final CalenderService calenderService;
    private final MeetingService meetingService;
    private final TherapyService therapyService;

    private long calenderIdLocal ;
    private PatientDto patient;

    public UserAddMeetingController(UserService userService, CalenderService calenderService, MeetingService meetingService, TherapyService therapyService) {
        this.userService = userService;
        this.calenderService = calenderService;
        this.meetingService = meetingService;
        this.therapyService = therapyService;
    }

    @GetMapping("/termin/{calenderId}")
    public String addMeetingByUserForm(@PathVariable long calenderId, Model model) {
        //1. wybranie z kalendarza
        CalenderDto calender = calenderService.findCalenderByIdIfFreeIsTrue(calenderId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("calender", calender);

        List<TherapyDto> allTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", allTherapies);

        PatientDto patientToSave = new PatientDto();
        model.addAttribute("patientToSave", patientToSave);

        MeetingToSaveDto meetingToSaveDto = new MeetingToSaveDto();
        model.addAttribute("meetingSave", meetingToSaveDto);
        calenderIdLocal = calenderId;
        return "user/meeting-form";
    }

    @PostMapping("/termin/add")
    public String addMeetingWithNewPatient(@ModelAttribute MeetingToSaveDto meetingToSaveDto, PatientDto patientToSave, Model model, RedirectAttributes redirectAttributes){
        model.addAttribute("meetingSave", meetingToSaveDto);
        meetingToSaveDto.setCalender(calenderIdLocal);
        meetingService.addMeetingWithNewPatient(patientToSave, meetingToSaveDto);
        redirectAttributes.addFlashAttribute(
                HomeController.NOTIFICATION_ATTRIBUTE,
                "Pacjent <b>%s %s </b> pseudonim <b>%s</b> został zapisany na <b>%s</b> na dzień <b>%s</b> "
                        .formatted(
                                patientToSave.getName(),
                                patientToSave.getSurname(),
                                patientToSave.getNick(),
                                therapyService.findTherapyById(meetingToSaveDto.getTherapy()).map(TherapyDto::getKindOfTherapy).orElse("Undefined"),
                                calenderService.findCalenderByIdIfFreeIsTrue(meetingToSaveDto.getCalender()).map(CalenderDto::getDayof).orElse("Undefined"))
        );
        return "redirect:/";
    };

}
