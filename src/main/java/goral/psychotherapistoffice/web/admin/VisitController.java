package goral.psychotherapistoffice.web.admin;

import goral.psychotherapistoffice.domain.counter.CounterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.HashMap;
import java.util.Map;

@Controller
public class VisitController {

    private final CounterService counterService;

    @Autowired
    public VisitController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/admin/visits")
    public String visits(Model model) {
        Map<String, Integer> urlVisits = new HashMap<>();
        urlVisits.put("/", counterService.getVisitCount("/"));
        urlVisits.put("/user", counterService.getVisitCount("/user"));
        urlVisits.put("/omnie", counterService.getVisitCount("/omnie"));
        urlVisits.put("http://localhost:8080/offerta", counterService.getVisitCount("http://localhost:8080/offerta"));

        model.addAttribute("urlVisits", urlVisits);
        return "admin/visits";
    }
}
