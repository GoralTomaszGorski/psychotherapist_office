package goral.psychotherapistoffice.web;


import goral.psychotherapistoffice.domain.counter.CounterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutMeController {

    private final CounterService counterService;

    public AboutMeController(CounterService counterService) {
        this.counterService = counterService;
    }


    @GetMapping("/omnie")
    public String therms(Model model, HttpServletRequest request){
        counterService.httpParameterToIncrement(request);
        int count = counterService.getCountForUrl("/omnie");
        model.addAttribute("count", count);
        model.addAttribute("heading", "Ewa Górska psychoterapeuta, psycholog, mediator, pedagog");
        return "about-me";
    }

}
