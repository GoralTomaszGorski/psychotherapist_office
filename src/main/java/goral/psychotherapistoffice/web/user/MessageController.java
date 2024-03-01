package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.messeges.MessageService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/mail")
public class MessageController {

    private MessageService messageService;

    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("/")
    public String therms(Model model){
        model.addAttribute("headingFreeTherm", "Wolne terminy");
        model.addAttribute("descriptionFreeTherms", "Wybierz dogodny dla siebie termin i wyślij prośbę o rezerwację wizyty");
        return "user/mail-form";
    }

    @PostMapping("/send")
    public String sendMail(@RequestParam String to, String from, String subject, String body){
        return messageService.sendMessage(to, from, subject, body);
    }
}
