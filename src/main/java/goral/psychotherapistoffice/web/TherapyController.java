package goral.psychotherapistoffice.web;

import goral.psychotherapistoffice.domain.counter.CounterService;
import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class TherapyController {
    private final TherapyService therapyService;
    private final CounterService counterService;

    public TherapyController(TherapyService therapyService, CounterService counterService) {
        this.therapyService = therapyService;
        this.counterService = counterService;
    }

    @GetMapping("/offerta")
    public String offers(Model model, HttpServletRequest request){
        counterService.httpParameterToIncrement(request);
        List<TherapyDto> kindOfTherapies = therapyService.findAllTherapies();
        model.addAttribute("therapyHeading", "Oferta");
        model.addAttribute("therapies", kindOfTherapies);
        return "offer";
    }

}
