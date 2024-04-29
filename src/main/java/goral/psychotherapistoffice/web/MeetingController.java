package goral.psychotherapistoffice.web;

import goral.psychotherapistoffice.domain.counter.CounterService;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;


@Controller
public class MeetingController {
    private final MeetingService meetingService;
    private final CounterService counterService;

    public MeetingController(MeetingService meetingService,
                             CounterService counterService) {
        this.meetingService = meetingService;
        this.counterService = counterService;
    }


    @GetMapping("/spotkania")
    public String meeting(Model model, HttpServletRequest request){
        counterService.httpParameterToIncrement(request);
        List<MeetingDto> meetingsTherms = meetingService.findAllMeetings();
        model.addAttribute("heading", "Ustalone terminy spotkań");
        model.addAttribute("description", "Sprawdź termin swojej wizyty");
        model.addAttribute("meetingsTherms", meetingsTherms);
        return "meeting";
    }

}
