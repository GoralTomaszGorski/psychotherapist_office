package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.domain.user.UserService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

public class ChangePasswordController {
    private final UserService userService;

    public ChangePasswordController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/change-password")
    String changePassword(@RequestParam String newPassword,
                          RedirectAttributes redirectAttributes) {
        userService.changeCurrentUserPassword(newPassword);
        return "redirect:/logout";
    }
}
