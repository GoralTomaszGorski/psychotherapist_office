package goral.psychotherapistoffice.web.user;


import goral.psychotherapistoffice.domain.counter.CounterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    public static final String NOTIFICATION_ATTRIBUTE = "userNotification";

    private final CounterService counterService;

    public UserController(CounterService counterService) {
        this.counterService = counterService;
    }


    @GetMapping("/user")
    public String getUserPanel(
        HttpServletRequest request){
            counterService.httpParameterToIncrement(request);
        return "user/user";
    }
}

