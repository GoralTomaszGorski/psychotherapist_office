package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.domain.counter.CounterService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    private final CounterService counterService;

    public LoginController(CounterService counterService) {
        this.counterService = counterService;
    }


    @GetMapping("/login")
    public String loginForm(HttpServletRequest request){
        counterService.httpParameterToIncrement(request);
        return "registration_login/login-form";
    }

}