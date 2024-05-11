package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.domain.messeges.MessageService;
import goral.psychotherapistoffice.domain.user.*;
import goral.psychotherapistoffice.domain.user.Dto.UserCredentialsDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
public class ChangePasswordController {
    private final UserService userService;
    private final UserRepository userRepository;
    private final MessageService messageService;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;
    private final  PasswordEncoder passwordEncoder;


    public ChangePasswordController(UserService userService,
                                    UserRepository userRepository, MessageService messageService, ChangePasswordTokenRepository changePasswordTokenRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.messageService = messageService;
        this.changePasswordTokenRepository = changePasswordTokenRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/forgot-password")
    public String forgotPassword(Model model) {
        UserCredentialsDto userCredentialsDto = new UserCredentialsDto();
        model.addAttribute("userCredentialsDto", userCredentialsDto);
        return "registration_login/forgot-Password";
    }

    @PostMapping("/forgot-password")
    public String forgotPasswordProcess(@ModelAttribute("message")
                                        UserCredentialsDto userCredentialsDto) {
        String output = "";
        User user = userRepository.findUsersByEmail(
                userCredentialsDto.getEmail());
        String resetLink = userService.generateResetToken(user);
        if (user != null) {
            output = messageService.sendMailToResetPassword(
                    userCredentialsDto, resetLink);
        }
        if (output.equals("success")) {
            return "redirect:/forgot-password?success";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/reset-password/{token}")
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
        return "redirect:/forgot-password?error";
    }

    @PostMapping("/reset-password")
    public String passwordResetProcess(
            @ModelAttribute UserCredentialsDto userCredentialsDto) {
        User user = userRepository.findUsersByEmail(
                userCredentialsDto.getEmail());
        if (user != null) {
           user.setPassword(passwordEncoder.encode(
                    userCredentialsDto.getPassword()));
           userRepository.save(user);
        }
        return "redirect:/login?new_pass";
    }
}
