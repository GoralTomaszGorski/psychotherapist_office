package goral.psychotherapistoffice.web;

import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.server.ResponseStatusException;

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
        model.addAttribute("description", "Sprawdz termin swojej wizyty");
        model.addAttribute("meetingThermsOccupied", meetingsTherms);
        return "meeting";
    }

}
