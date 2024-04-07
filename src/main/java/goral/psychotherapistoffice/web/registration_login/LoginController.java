package goral.psychotherapistoffice.web.registration_login;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    @GetMapping("/login")
    public String loginForm() {
        return "registration_login/login-form";
    }

}