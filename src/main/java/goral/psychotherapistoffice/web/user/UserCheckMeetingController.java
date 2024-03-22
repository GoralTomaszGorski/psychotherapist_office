package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserCheckMeetingController {

    @Controller
    public class MeetingController {
        private final MeetingService meetingService;

        public MeetingController(MeetingService meetingService) {
            this.meetingService = meetingService;
        }


        @GetMapping("/user/spotkania")
        public String meetingByUser(Model model) {
            List<MeetingDto> meetingsTherms = meetingService.findMeetingByUserMail();
            model.addAttribute("heading", "Ustalone terminy spotkań");
            model.addAttribute("description", "Sprawdz termin swojej wizyty");
            model.addAttribute("meetingsTherms", meetingsTherms);
            return "meeting";
        }
    }

}
