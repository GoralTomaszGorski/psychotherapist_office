package goral.psychotherapistoffice.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {

    public static final String NOTIFICATION_ATTRIBUTE = "notification";


    @GetMapping("/login")
    public String loginForm() {
        return "login-form";
    }
}