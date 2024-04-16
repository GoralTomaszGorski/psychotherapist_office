package goral.psychotherapistoffice.web;

import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TherapyController {
    private final TherapyService therapyService;

    public TherapyController(TherapyService therapyService) {
        this.therapyService = therapyService;
    }

    @GetMapping("/offerta")
    public String offers(Model model){
        List<TherapyDto> kindOfTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapyHeading", "Oferta");
        model.addAttribute("therapies", kindOfTherapies);
        return "offer";
    }

}
