package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.domain.user.Dto.UserCredentialsDto;
import goral.psychotherapistoffice.domain.user.UserRegistrationDto;
import goral.psychotherapistoffice.web.admin.AdminController;
import org.springframework.ui.Model;
import goral.psychotherapistoffice.domain.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class RegistrationController {
    private final UserService userService;

    public RegistrationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/rejestracja")
    public String registrationForm(Model model) {
        UserRegistrationDto userRegistration = new UserRegistrationDto();
        model.addAttribute("user", userRegistration);
        return "registration_login/registration-form";
    }

    @PostMapping("/rejestracja")
    public String register(UserRegistrationDto userRegistration, RedirectAttributes redirectAttributes) {
        userService.registerUserWithDefaultRole(userRegistration);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Pomyślnie zarejestrowano <b>%s</b> "
                        .formatted(
                                userService.findCredentialsByEmail(userRegistration.getEmail())
                                        .map(UserCredentialsDto::getEmail).orElse("Undefined")
                        )
        );
        return "redirect:/";
    }
}
