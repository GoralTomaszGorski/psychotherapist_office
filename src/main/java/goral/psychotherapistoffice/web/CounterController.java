package goral.psychotherapistoffice.web;

import goral.psychotherapistoffice.domain.counter.CounterService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class CounterController {

    private final CounterService counterService;

    public CounterController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/admin/visits")
    public String visits(Model model){
        model.addAttribute("heading", "Ewa Górska psychoterapeuta, psycholog, pedagog, mediator");

        int homeCounter = (counterService.getCountForUrl("/"));
        model.addAttribute("homeCounter", homeCounter);
        int omnnieCounter = (counterService.getCountForUrl("/omnie"));
        model.addAttribute("omnnieCounter", omnnieCounter);
        int ofertaCounter = (counterService.getCountForUrl("/offerta"));
        model.addAttribute("ofertaCounter", ofertaCounter);
        int mailCounter = (counterService.getCountForUrl("/mail"));
        model.addAttribute("mailCounter", mailCounter);
        int userCounter = (counterService.getCountForUrl("/user"));
        model.addAttribute("userCounter", userCounter);
        int adminCounter = (counterService.getCountForUrl("/admin"));
        model.addAttribute("adminCounter", adminCounter);
        int spotkaniaCounter = (counterService.getCountForUrl("/spotkania"));
        model.addAttribute("spotkaniaCounter", spotkaniaCounter);
        int terminyCounter = (counterService.getCountForUrl("/terminy"));
        model.addAttribute("terminyCounter", terminyCounter);
        int loginCounter = (counterService.getCountForUrl("/login"));
        model.addAttribute("loginCounter", loginCounter);
        return "admin/visits";
    }
}
