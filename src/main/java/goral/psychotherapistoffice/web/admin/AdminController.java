package goral.psychotherapistoffice.web.admin;


import goral.psychotherapistoffice.domain.counter.CounterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {
    //stała z TherapyManagementController
    public static final String NOTIFICATION_ATTRIBUTE = "notification";
    private final CounterService counterService;

    public AdminController(CounterService counterService) {
        this.counterService = counterService;
    }

    @GetMapping("/admin")
    public String getAdminPanel(HttpServletRequest request){
        counterService.httpParameterToIncrement(request);
        return "admin/admin";
    }

}
