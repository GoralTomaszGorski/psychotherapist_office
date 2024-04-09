package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.domain.messeges.MessageService;
import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import goral.psychotherapistoffice.domain.user.*;
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


    public ChangePasswordController(UserService userService, UserRepository userRepository, MessageService messageService, ChangePasswordTokenRepository changePasswordTokenRepository) {
        this.userService = userService;
        this.userRepository = userRepository;
        this.messageService = messageService;
        this.changePasswordTokenRepository = changePasswordTokenRepository;
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        MessageDto messageDto = new MessageDto();
        model.addAttribute("mailDto", messageDto);
        return "registration_login/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPasswordProcess(@ModelAttribute("message") MessageDto messageDto) {
        String output = "";
        User user = userRepository.findUsersByEmail(messageDto.getRecipient());
        String resetLink = userService.generateResetToken(user);

        if (user != null) {
            messageService.sendMail(messageDto, resetLink);
        }

        if (output.equals("success")) {
            return "redirect:forgotPassword?success";
        }
        return "redirect:/login?error";
    }

    @GetMapping("/resetPassword/{token}")
    public String resetPasswordForm(@PathVariable String token, Model model) {
        ChangePasswordToken reset = changePasswordTokenRepository.findByToken(token);
        if (reset != null && userService.hasExipred(reset.getExpiryDateTime())) {
            model.addAttribute("email", reset.getUser().getEmail());
            return "resetPassword";
        }
        return "redirect:/forgotPassword?error";
    }

//    @PostMapping("/resetPassword")
//    public String passwordResetProcess(@ModelAttribute UserCredentialsDto userCredentialsDto) {
//        User user = userRepository.findByEmail(userCredentialsDto.getEmail());
//        if (user != null) {
//            user.setPassword(passwordEncoder.encode(userCredentialsDto.getPassword()));
//            userRepository.save(user);
//        }
//        return "redirect:/login";
//    }

}
