package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.domain.user.*;
import goral.psychotherapistoffice.domain.user.Dto.UserCredentialsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ChangePasswordController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;


    public ChangePasswordController(
            UserService userService, UserRepository userRepository, ChangePasswordTokenRepository changePasswordTokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.changePasswordTokenRepository = changePasswordTokenRepository;
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "registration_login/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPassordProcess(@ModelAttribute UserCredentialsDto userCredentialsDto) {
        String output = "";

        User user = userRepository.findUsersByEmail(userCredentialsDto.getEmail());
        if (user != null) {
            output = userService.sendEmailToken(user);
        }
        if (output.equals("success")) {
            return "redirect:/forgotPassword?success";
        }
        return "redirect:/login?error";
    }



}
