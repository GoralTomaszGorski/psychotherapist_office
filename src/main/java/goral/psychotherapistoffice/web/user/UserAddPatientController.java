package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import goral.psychotherapistoffice.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class UserAddPatientController {
    private final PatientService patientService;

    public UserAddPatientController(PatientService patientService) {
        this.patientService = patientService;
    }


    @GetMapping("/user/dodaj-pacjenta")
    public String addPatientFrom(Model model){
        PatientDto patientDto = new PatientDto();
        model.addAttribute("patientDto", patientDto);
        model.addAttribute("heading", "Podaj dane Pacjęta");
        model.addAttribute("description", "Dodaj informacje w celu umuwienia wizyty");
        return "user/add-patient-form";
    }

    @PostMapping("/user/dodaj-pacjenta")
    public String addPatientData(@ModelAttribute("patient")
                                     PatientDto patientDto, RedirectAttributes redirectAttributes){
        patientService.addPatient(patientDto);
        redirectAttributes.addFlashAttribute(
                UserController.NOTIFICATION_ATTRIBUTE,
                "Pacjent <b>%s %s </b> pseudonim <b>%s</b> został zapisany "
                        .formatted(
                                patientDto.getName(),
                                patientDto.getSurname(),
                                patientDto.getNick())
        );
        return "redirect:/user";
    }

}
