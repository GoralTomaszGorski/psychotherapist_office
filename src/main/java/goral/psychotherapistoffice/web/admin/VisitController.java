package goral.psychotherapistoffice.web.admin;

import goral.psychotherapistoffice.domain.counter.CounterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.HashMap;
import java.util.Map;

@Controller
public class VisitController {

    private final CounterService counterService;

    public VisitController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/admin/visits")
    public String visits(HttpServletRequest request, Model model) {
        String sessionId = request.getSession().getId();

        // Pobierz dane z usługi licznika
        Map<String, Integer> urlVisits = new HashMap<>();
        urlVisits.put("Strona główna", counterService.getVisitCount("/"));
        urlVisits.put("Strona użytkownika", counterService.getVisitCount("/user"));
        urlVisits.put("O nas", counterService.getVisitCount("/omnie"));
        urlVisits.put("Oferta", counterService.getVisitCount("http://localhost:8080/offerta"));

        Map<String, Integer> urlRefreshes = new HashMap<>();
        urlRefreshes.put("Strona główna", counterService.getRefreshCount("/", sessionId, ""));
        urlRefreshes.put("Strona użytkownika", counterService.getRefreshCount("/user", sessionId, ""));
        urlRefreshes.put("O nas", counterService.getRefreshCount("/omnie", sessionId, ""));
        urlRefreshes.put("Oferta", counterService.getRefreshCount("http://localhost:8080/offerta", sessionId, ""));

        model.addAttribute("urlVisits", urlVisits);
        model.addAttribute("urlRefreshes", urlRefreshes);
        return "admin/visits";
    }
}