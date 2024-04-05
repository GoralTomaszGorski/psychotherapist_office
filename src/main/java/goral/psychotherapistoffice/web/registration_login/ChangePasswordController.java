package goral.psychotherapistoffice.web.registration_login;

import goral.psychotherapistoffice.config.security.CustomUserDetailsService;
import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import goral.psychotherapistoffice.domain.user.*;
import goral.psychotherapistoffice.domain.user.Dto.UserCredentialsDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


@Controller
public class ChangePasswordController {
    private final CustomUserDetailsService userDetailsService;
    private final UserService userService;
    private final UserRepository userRepository;
    private final ChangePasswordTokenRepository changePasswordTokenRepository;


    public ChangePasswordController(
            CustomUserDetailsService userDetailsService, UserService userService, UserRepository userRepository, ChangePasswordTokenRepository changePasswordTokenRepository) {
        this.userDetailsService = userDetailsService;
        this.userService = userService;
        this.userRepository = userRepository;
        this.changePasswordTokenRepository = changePasswordTokenRepository;
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword(Model model) {
        MessageDto messageDto = new MessageDto();
        model.addAttribute("mailDto", messageDto);
        return "registration_login/forgotPassword";
    }

    @PostMapping("/forgotPassword")
    public String forgotPasswordProcess(@ModelAttribute("message") MessageDto messageDto, RedirectAttributes redirectAttributes) {
        String output = "";
        User user = userRepository.findUsersByEmail(messageDto.getRecipient());

        if (user != null) {
            output = userService.sendEmailToken(user, messageDto);
        }

        if (output.equals("success")) {
            return "redirect:forgotPassword?success";
        }
        return "redirect:forgotPassword?error";
    }



}
