package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
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


    @GetMapping("/user/dodaj-pacjenta")
    public String addPatientFrom(Model model){
        PatientDto patientDto = new PatientDto();
        model.addAttribute("patientDto", patientDto);
        model.addAttribute("heading",
                "Podaj swoje dane, aby umówić wizytę");
        model.addAttribute("description",
                "Twoje dane będzie widział jedynie terapeuta. Ogólnodostępne będzie jednynie NICK. ");
        model.addAttribute("additinal",
                "Jeśli nie chcesz podawać nicku zostanie wygenerowny automatycznie 3 ostatnie cyfry z nr tel, " +
                        "2 pierwsze litery nazwiska i pierwsza litera imienia.");
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
