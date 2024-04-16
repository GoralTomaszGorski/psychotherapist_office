package goral.psychotherapistoffice.web;

import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


import java.util.List;


@Controller
public class MeetingController {
    private final MeetingService meetingService;

    public MeetingController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }


    @GetMapping("/spotkania")
    public String meeting(Model model){
        List<MeetingDto> meetingsTherms = meetingService.findAllMeetings();
        model.addAttribute("heading", "Ustalone terminy spotkań");
        model.addAttribute("description", "Sprawdź termin swojej wizyty");
        model.addAttribute("meetingsTherms", meetingsTherms);
        return "meeting";
    }

}
