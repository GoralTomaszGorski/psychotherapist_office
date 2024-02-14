package goral.psychotherapistoffice.web;


import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;

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

    /*    home controller włsciwie calender controller - wyrzuca wszyskie terminy bo tak Ewcia chiała do przegadania czy nie lepiej same wolene terminy teraz to jest w zakładce terminy*/

    @GetMapping("/")
    public String therms(Model model){
        List<CalenderDto>allTherms = calenderService.findAllTherms();
        model.addAttribute("headingAllTherm", "Czas pracy gabinetu");
        model.addAttribute("descriptionAllTherms", "Kalendarz spotkań i wolne terminy");
        model.addAttribute("alltherms", allTherms);
        return "index";
    }





}
