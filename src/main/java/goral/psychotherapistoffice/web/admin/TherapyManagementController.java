package goral.psychotherapistoffice.web.admin;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class TherapyManagementController {
    private final TherapyService therapyService;
    private final CalenderService calenderService;

    public TherapyManagementController(TherapyService therapyService, CalenderService calenderService) {
        this.therapyService = therapyService;
        this.calenderService = calenderService;
    }

    @GetMapping("/admin/dodaj-terapie")
    public String addTherapyForm(Model model) {
        TherapyDto therapyDto = new TherapyDto();
        model.addAttribute("therapyDto", therapyDto);
        return "admin/therapy-form";
    }

    @PostMapping("/admin/dodaj-terapie")
    public String addTherapy(@ModelAttribute("therapy") TherapyDto therapyDto, RedirectAttributes redirectAttributes){
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

}
