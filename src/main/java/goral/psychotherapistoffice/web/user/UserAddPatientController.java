package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserAddPatientController {
    private final PatientService patientService;

    public UserAddPatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/user/dodaj-pacjeta")
    public String addPatientFrom(Model model){
        PatientDto patientDto = new PatientDto();
        model.addAttribute("patientDto", patientDto);
        return "user/add-patient-form";
    }


    @PostMapping("/user/dodaj-pacjeta")
    public String addPatient(@ModelAttribute("patient") PatientDto patientDto, RedirectAttributes redirectAttributes){
        patientService.addPatient(patientDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Pacjęt <b>%s %s </b> pseudonim <b>%s</b> został zapisany "
                        .formatted(
                                patientDto.getName(),
                                patientDto.getSurname(),
                                patientDto.getNick())
        );
        return "redirect:/user";
    }

}
