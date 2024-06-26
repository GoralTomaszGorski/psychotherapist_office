package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.counter.CounterService;
import goral.psychotherapistoffice.domain.messeges.MessageService;
import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import goral.psychotherapistoffice.web.HomeController;
import goral.psychotherapistoffice.web.admin.AdminController;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mail")
public class MessageController {

    private final MessageService messageService;
    private final CounterService counterService;

    public MessageController(MessageService messageService, CounterService counterService) {
        this.messageService = messageService;
        this.counterService = counterService;
    }

    @GetMapping("")
    public String sendMail(Model model, HttpServletRequest request){
        counterService.httpParameterToIncrement(request);
        model.addAttribute("headingFreeTherm", "Wolne terminy");
        model.addAttribute("descriptionFreeTherms", "Wybierz dogodny dla siebie termin i wyślij prośbę o rezerwację wizyty");
        MessageDto messageDto = new MessageDto();
        model.addAttribute("mailDto", messageDto);
        return "user/mail-form";
    }


    @PostMapping("")
    public String sendMail(@ModelAttribute("message") MessageDto messageDto, RedirectAttributes redirectAttributes) {
        messageService.sendMailByMessagebox(messageDto);
        redirectAttributes.addFlashAttribute(
                HomeController.NOTIFICATION_ATTRIBUTE,
                "widomość <b>%s</b> została wysłana do terapeuty "
                        .formatted(
                        messageDto.getSubject()
                        )
        );
        return "redirect:/";
    }
}
