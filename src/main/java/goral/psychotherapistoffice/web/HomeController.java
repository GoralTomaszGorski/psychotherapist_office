package goral.psychotherapistoffice.web;


import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;

import goral.psychotherapistoffice.domain.counter.Counter;
import goral.psychotherapistoffice.domain.counter.CounterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class HomeController {

    public static final String NOTIFICATION_ATTRIBUTE = "notification";

    private final CalenderService calenderService;

    public HomeController(CalenderService calenderService){
        this.calenderService = calenderService;
    }

    @GetMapping("/")
    public String therms(Model model, HttpServletRequest request){
        List<CalenderDto>allTherms = calenderService.findAllTherms();

        model.addAttribute("headingAllTherm", "Rejestracja internetowa");
        model.addAttribute("descriptionHead",
                "Termin sesji jest stały i cotygodniowy zgodnie ze standardami prowadzenia psychoterapii. " +
                        "Podczas pierwszej sesji (konsultacyjnej) wspólnie ustalimy czy rozpoczynamy cykliczne spotkania.");
        model.addAttribute("descriptionAllTherms",
                "Kliknij na wybrany wolny termin (zielona ikonka):");
        model.addAttribute("alltherms", allTherms);

        return "index";
    }
}
