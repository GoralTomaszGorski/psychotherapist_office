package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.messeges.MessageService;
import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import goral.psychotherapistoffice.web.admin.AdminController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/mail")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("")
    public String sendMail(Model model){
        model.addAttribute("headingFreeTherm", "Wolne terminy");
        model.addAttribute("descriptionFreeTherms", "Wybierz dogodny dla siebie termin i wyślij prośbę o rezerwację wizyty");
        MessageDto messageDto = new MessageDto();
        model.addAttribute("mailDto", messageDto);
        return "user/mail-form";
    }

    @PostMapping("")
    public String sendMail(@ModelAttribute("message") MessageDto messageDto, RedirectAttributes redirectAttributes){
//    public String sendMail(@RequestParam String to, String from, String subject, String body){
        messageService.sendMessage(messageDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "widomość <b>%s</b> została wysłana do <b>%s</b>  "
                        .formatted(
                                messageDto.getSubject(),
                                messageDto.getTo())
                        );
        return "index";
    }
}
