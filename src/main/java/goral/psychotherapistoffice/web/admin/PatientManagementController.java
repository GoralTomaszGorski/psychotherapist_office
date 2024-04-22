package goral.psychotherapistoffice.web.admin;


import goral.psychotherapistoffice.domain.exception.DeletePatientException;
import goral.psychotherapistoffice.domain.patient.PatientService;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
@RequestMapping("/admin")

public class PatientManagementController {
    private final PatientService patientService;

    public PatientManagementController(PatientService patientService) {
        this.patientService = patientService;
    }

    @GetMapping("/dodaj-pacjenta")
    public String addPatientFrom(Model model){
        PatientDto patientDto = new PatientDto();
        model.addAttribute("patientDto", patientDto);
        model.addAttribute("heading",
                "Podaj dane pacjęta, aby umówić wizytę");
        model.addAttribute("description",
                "Dane będzie widział jedynie terapeuta. Ogólnodostępne będzie jednynie Nick. ");
        return "user/add-patient-form";
    }

    @PostMapping("/dodaj-pacjenta")
    public String addPatient(@ModelAttribute("patient") PatientDto patientDto, RedirectAttributes redirectAttributes){
        patientService.addPatient(patientDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Pacjent <b>%s %s </b> został zapisany "
                        .formatted(
                                patientDto.getName(),
                                patientDto.getSurname()
                        )
        );
        return "redirect:/";
    }

    @GetMapping("/pacjenci")
    public String patients(@RequestParam(required = false) String keyword,  Model model) {
        List<PatientDto> patients;
        if (keyword == null)  {
            patients = patientService.findAllPatients();
        } else {
            patients = patientService.findBySurnameOrName(keyword);
        }
        model.addAttribute("patientHeading", "Sprawdź dane pacjentów");
        model.addAttribute("patients", patients);
        return "admin/patients";
    }

    @GetMapping("/pacjeci/delete/{id}")
    public String deletePatient(
            @PathVariable (name = "id") Long id,
            RedirectAttributes redirectAttributes) {
        try {
            patientService.deletePatient(id);
        } catch (Exception e) {
            throw new DeletePatientException();
        }

        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Usunięto Pacjenta "
        );
        return "redirect:/admin";
    }
}
