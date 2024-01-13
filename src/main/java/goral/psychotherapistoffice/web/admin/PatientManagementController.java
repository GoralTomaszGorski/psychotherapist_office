package goral.psychotherapistoffice.web.admin;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class PatientManagementController {
    private final PatientService patientService;
    private final CalenderService calenderService;

    public PatientManagementController(PatientService patientService, CalenderService calenderService) {
        this.patientService = patientService;
        this.calenderService = calenderService;
    }

    @GetMapping("/admin/dodaj-pacjeta")
    public String addPatientFrom(Model model){
        PatientDto patientDto = new PatientDto();
        model.addAttribute("patientDto", patientDto);
        return "admin/admin-add-patient-form";
    }


    @PostMapping("/admin/dodaj-pacjeta")
    public String addPatient(@ModelAttribute("patient") PatientDto patientDto, RedirectAttributes redirectAttributes){
        patientService.addPatient(patientDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Pacjęt %s %s pseudonim %s został zapisany "
                        .formatted(
                                patientDto.getName(),
                                patientDto.getSurname(),
                                patientDto.getNick())
        );
        return "redirect:/admin";
    }

    @GetMapping("/admin/pacjeci")
    public String patients(Model model){
        List<PatientDto> allPatients = patientService.findAllPatients();
        model.addAttribute("patientHeading", "Sprawdź dane pacjętów");
        model.addAttribute("patients", allPatients);
        return "admin/patients";
    }
}
