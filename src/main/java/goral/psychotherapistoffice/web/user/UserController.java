package goral.psychotherapistoffice.web.user;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    public static final String NOTIFICATION_ATTRIBUTE = "userNotification";

    @GetMapping("/user")
    public String getUserPanel() {
        return "user/user";
    }
}

