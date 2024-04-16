package goral.psychotherapistoffice.web.admin;

import goral.psychotherapistoffice.domain.exception.DeleteTherapyException;
import goral.psychotherapistoffice.domain.exception.TherapyNotFoundException;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/admin")
public class TherapyManagementController {
    private final TherapyService therapyService;


    public TherapyManagementController(TherapyService therapyService) {
        this.therapyService = therapyService;
    }

    @GetMapping("/offer/add")
    public String addTherapyForm(Model model) {
        TherapyDto therapyDto = new TherapyDto();
        model.addAttribute("therapyDto", therapyDto);
        model.addAttribute("heading",
                "Dodaj rodzaj terapii, opis, cenę. Nie wpisuj Id, zostanie wygenerowane automatycznie.");
        return "admin/therapy-form";
    }

    @PostMapping("/offer/add")
    public String addTherapy(@ModelAttribute("therapy")
                                 TherapyDto therapyDto, RedirectAttributes redirectAttributes){
        therapyService.addTherapy(therapyDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Terapia <b>%s</b> została zapisana cena <b>%s</b>  zł"
                        .formatted(
                                therapyDto.getKindOfTherapy(),
                                therapyDto.getPrice())
        );
        return "redirect:/admin";
    }
    @GetMapping("/offer/view")
    public String showTherapies(Model model) {
        List<TherapyDto> therapies = therapyService.findAllTherapies();
        model.addAttribute("therapies", therapies);
        model.addAttribute("heading",
                "Edytuj/usuń rodzaj terapii, opis, cenę");
        return "admin/offer-view";
    }
    @GetMapping("/offer/edit/{id}")
    public String showEditTherapy(@PathVariable("id") long id, Model model)
            throws TherapyNotFoundException{
        TherapyDto therapyDto = therapyService.findTherapyById(id)
                .orElseThrow(TherapyNotFoundException::new);
        model.addAttribute("heading",
                "Edytuj rodzaj terapii, opis, cenę");
        model.addAttribute("therapyDto", therapyDto);
        return "admin/therapy-form";
    }

    @PostMapping("/offer/edit/{id}")
    public String showEditTherapy(@PathVariable("id") TherapyDto therapyDto, RedirectAttributes redirectAttributes){
        therapyService.editTherapy(therapyDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Terapia <b>%s</b> została zapisana cena <b>%s</b>  zł"
                        .formatted(
                                therapyDto.getKindOfTherapy(),
                                therapyDto.getPrice())
        );
        return "redirect:/admin";
    }

    @GetMapping("/offer/delete/{id}")
    public String deletePatient(
            @PathVariable (name = "id") Long id,
            RedirectAttributes redirectAttributes) {
        try {
            therapyService.deleteTherapy(id);
        } catch (Exception e) {
            throw new DeleteTherapyException(HttpStatus.BAD_REQUEST);
        }
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Usunięto rodzaj terapii "
        );
        return "redirect:/admin";
    }

}
