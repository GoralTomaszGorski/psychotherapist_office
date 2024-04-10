package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.domain.messeges.MessageService;
import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import goral.psychotherapistoffice.domain.user.*;
import goral.psychotherapistoffice.domain.user.Dto.UserCredentialsDto;
import org.springframework.boot.Banner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ChangePasswordController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final MessageService messageService;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;
    private PasswordEncoder passwordEncoder;


    public ChangePasswordController(UserService userService,
                                    UserRepository userRepository, MessageService messageService, ChangePasswordTokenRepository changePasswordTokenRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.messageService = messageService;
        this.changePasswordTokenRepository = changePasswordTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto();
        model.addAttribute("userCredentialsDto", userCredentialsDto);
        return "registration_login/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPasswordProcess(@ModelAttribute("message")
                                        UserCredentialsDto userCredentialsDto) {
        String output = "";
        User user = userRepository.findUsersByEmail(
                userCredentialsDto.getEmail());
        String resetLink = userService.generateResetToken(user);
        if (user != null) {
            output = messageService.sendMail(
                    userCredentialsDto, resetLink);
        }
        if (output.equals("success")) {
            return "redirect:/forgotPassword?success";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(
            @PathVariable String token, Model model) {
        ChangePasswordToken reset = changePasswordTokenRepository.findByToken(token);
        if (reset != null && userService.hasExipred(
                reset.getExpiryDateTime())) {
            model.addAttribute("email",
                    reset.getUser().getEmail());
            model.addAttribute("password",
                    reset.getUser().getPassword());
            return "registration_login/change-password-form";
        }
        return "redirect:/forgotPassword?error";
    }

    @PostMapping("/resetPassword")
    public String passwordResetProcess(
            @ModelAttribute UserCredentialsDto userCredentialsDto) {
        User user = userRepository.findUsersByEmail(
                userCredentialsDto.getEmail());
        String output = "";

        if (user != null) {
            output =
            user.setPassword(passwordEncoder.encode(
                    userCredentialsDto.getPassword()));
            userRepository.save(user);
        }
        if (output.equals("success")) {
            return "redirect:/resetPassword?success";
        }
        return "redirect:/resetPassword?error";

    }
}
