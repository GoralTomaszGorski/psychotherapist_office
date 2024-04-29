package goral.psychotherapistoffice.web;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.counter.CounterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class CalenderController {
    private final CalenderService calenderService;
    private final CounterService counterService;

    public CalenderController(CalenderService calenderService, CounterService counterService) {
        this.calenderService = calenderService;
        this.counterService = counterService;
    }

    @GetMapping("/terminy")
    public String therms(Model model, HttpServletRequest request){
        counterService.httpParameterToIncrement(request);
        List<CalenderDto> freeTherms = calenderService.findAllFreeTherms();
        model.addAttribute("headingFreeTherm", "Wolne terminy");
        model.addAttribute("descriptionFreeTherms", "Wybierz dogodny dla siebie termin i wyślij prośbę o rezerwację wizyty");
        model.addAttribute("allFreeTherms", freeTherms);
        return "free-therms";
    }
}
