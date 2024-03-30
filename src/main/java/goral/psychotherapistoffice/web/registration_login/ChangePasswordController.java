package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.domain.user.Dto.UserCredentialsDto;
import goral.psychotherapistoffice.domain.user.User;
import goral.psychotherapistoffice.domain.user.UserRepository;
import goral.psychotherapistoffice.domain.user.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RequestMapping("/change-password")
@Controller
public class ChangePasswordController {
    private final UserService userService;
    private final UserRepository userRepository;


    public ChangePasswordController(
            UserService userService, UserRepository userRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
    }

    @GetMapping()
    public String changePasswordForm() {
        return "registration_login/change-password-form";
    }

    @PostMapping()
    public String changePassword(@ModelAttribute UserCredentialsDto userCredentialsDto) {
        String output = "";
        User user = userRepository.findUsersByEmail(userCredentialsDto.getEmail());
        if (user != null){
            output = userService.sendEmailToken(user);
        }
        if (output.equals("success")) {
            return "redirect:/change-password?success";
        }
        return "redirect:/login?error";


    }
}
