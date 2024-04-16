package goral.psychotherapistoffice.web;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AboutMeController {

    @GetMapping("/omnie")
    public String therms(Model model){
        model.addAttribute("heading", "Ewa Górska psychoterapeuta, psycholog, mediator");
        return "aboutMe";
    }
}
