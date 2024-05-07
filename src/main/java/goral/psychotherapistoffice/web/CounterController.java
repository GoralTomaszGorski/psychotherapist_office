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

        int home = (counterService.getCountForUrl("/"));
        model.addAttribute("home", home);
        int omnnieC = (counterService.getCountForUrl("/omnie"));
        model.addAttribute("omnieC", omnnieC);
        int offertaC = (counterService.getCountForUrl("/offerta"));
        model.addAttribute("offertaC", offertaC);
        int mail = (counterService.getCountForUrl("/mail"));
        model.addAttribute("mail", mail);
        int userC = (counterService.getCountForUrl("/user"));
        model.addAttribute("userC", userC);
        int adminC = (counterService.getCountForUrl("/admin"));
        model.addAttribute("adminC", adminC);
        int spotkaniaC = (counterService.getCountForUrl("/spotkania"));
        model.addAttribute("spotkaniaC", spotkaniaC);
        int terminyC = (counterService.getCountForUrl("/terminy"));
        model.addAttribute("terminyC", terminyC);
        int loginC = (counterService.getCountForUrl("/login"));
        model.addAttribute("loginC", loginC);
        return "admin/visits";
    }
}
