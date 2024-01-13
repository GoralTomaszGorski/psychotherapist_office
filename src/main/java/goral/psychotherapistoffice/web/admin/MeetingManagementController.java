package goral.psychotherapistoffice.web.admin;


import goral.psychotherapistoffice.domain.meeting.MeetingService;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MeetingManagementController {
    private final MeetingService meetingService;

    public MeetingManagementController(MeetingService meetingService) {
        this.meetingService = meetingService;
    }

    @GetMapping("admin/spotkania")
    public String meetingAdmin(Model model){
        List<MeetingDto> meetingsThermsForAdmin = meetingService.findAllMeetings();
        model.addAttribute("headingFA", "Terminy spotkań");
        model.addAttribute("descriptionFA", "Sprawdz terminy spotkań");
        model.addAttribute("meetingThermsOccupiedForAdmin", meetingsThermsForAdmin);
        return "admin/meeting-admin-view";
    }


}
