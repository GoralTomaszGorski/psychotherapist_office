package goral.psychotherapistoffice.web.admin;


import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/admin")
public class CalenderManagementController {

    private final CalenderService calenderService;

    public CalenderManagementController(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    @GetMapping("/dodaj-termin")
    public String addCalenderForm(Model model){
        CalenderDto calenderDto = new CalenderDto();
        model.addAttribute("calenderDto", calenderDto);
        model.addAttribute("heading", "Podaj dane nowego Terminu");
        model.addAttribute("description", "Dodaj informacje dotyczące nowego teminu spotkań dla pacjętów");
        return "admin/add-calender-form";
    }

    @PostMapping("/dodaj-termin")
    public String addCalender(@ModelAttribute("calender") CalenderDto calenderDto, RedirectAttributes redirectAttributes){
        calenderService.addCalender(calenderDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Termin nr <b>%S %s %s </b>  został dodany jest wolny"
                        .formatted(
                                calenderDto.getId(),
                                calenderDto.getDayof(),
                                calenderDto.getTime(),
                                calenderDto.isFree()
                        )
        );
        return "redirect:admin/admin";
    }

    @GetMapping("/calender-edit/{id}")
    public String showEditCalender(Model model,
                                   @PathVariable(name = "id") Long id){

        return "admin/calender-form";
    }
}
