package goral.psychotherapistoffice.web.user;

import goral.psychotherapistoffice.domain.counter.CounterService;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserCheckMeetingController {

    @Controller
    public static class MeetingController {
        private final MeetingService meetingService;
        private final CounterService counterService;


        public MeetingController(MeetingService meetingService, CounterService counterService) {
            this.meetingService = meetingService;
            this.counterService = counterService;
        }

        @GetMapping("/user/spotkania")
        public String meetingByUser(Model model,
            HttpServletRequest request){
                counterService.httpParameterToIncrement(request);
            List<MeetingDto> meetingsTherms = meetingService.findMeetingByUserMail();
            model.addAttribute("heading", "Ustalone terminy spotkań");
            model.addAttribute("description", "Sprawdź termin swojej wizyty");
            model.addAttribute("meetingsTherms", meetingsTherms);
            return "meeting";
        }
    }

}
