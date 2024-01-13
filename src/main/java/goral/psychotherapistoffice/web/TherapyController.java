package goral.psychotherapistoffice.web;

import goral.psychotherapistoffice.domain.therapy.TherapyService;
import goral.psychotherapistoffice.domain.therapy.dto.TherapyDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
        model.addAttribute("therapyHeading", "Wybierz terapię która Cię interesuje");
        model.addAttribute("therapies", kindOfTherapies);
        return "offer";
    }

    /*popsułem nie działa do poprawy później*/
    /*no przecież se naprawiłem;)*/
    @GetMapping("/offerta/{id}")
    public String getTherapy(@PathVariable long id, Model model) {
        TherapyDto therapyDto = therapyService.findTherapyById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        model.addAttribute("therapyId", therapyDto);
        return "offerId";
    }


}
