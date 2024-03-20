package goral.psychotherapistoffice.web.admin;


import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.exception.DeleteCalenderException;
import goral.psychotherapistoffice.domain.exception.DeletePatientException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/admin")
public class CalenderManagementController {

    private final CalenderService calenderService;

    public CalenderManagementController(CalenderService calenderService) {
        this.calenderService = calenderService;
    }

    @GetMapping("/dodaj-termin")
    public String addCalenderForm(Model model) {
        CalenderDto calenderDto = new CalenderDto();
        model.addAttribute("calenderDto", calenderDto);
        model.addAttribute("heading", "Podaj dane nowego Terminu w kalendarzu");
        model.addAttribute("description", "Dodaj informacje dotyczące nowego teminu spotkań dostępnego pacjętów");
        return "admin/calender-add-form";
    }

    @PostMapping("/dodaj-termin")
    public String addCalender(@ModelAttribute("calender") CalenderDto calenderDto, RedirectAttributes redirectAttributes) {
        calenderService.addCalender(calenderDto);
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Termin nr <b>%S %s %s </b>  został dodany jest wolny"
                        .formatted(
                                calenderDto.getId(),
                                calenderDto.getDayof(),
                                calenderDto.getTime()
                        )
        );
        return "redirect:/admin";
    }

    @GetMapping("/calender/view")
    public String showCalenderTherms(Model model) {
        List<CalenderDto> calenderTherms = calenderService.findAllTherms();
        model.addAttribute("calenderS", calenderTherms);
        model.addAttribute("heading", "Edytuj dane Terminu w kalendarzu");
        model.addAttribute("description", "Edytuj informacje dotyczące teminu spotkań dostępnego pacjętów");
        return "admin/calender-therms-view";
    }

    @GetMapping("/calender/edit/{id}")
    public String editCalender(Model model,
                               @PathVariable(name = "id") Long id) {
        Optional<CalenderDto> calenderDto = calenderService.findCalenderById(id);
        model.addAttribute("calenderDto", calenderDto);
        model.addAttribute("heading", "Edytuj dane Terminu w kalendarzu");
        model.addAttribute("description", "Edytuj informacje dotyczące teminu spotkań dostępnego pacjętów");
        return "admin/calender-add-form";
    }

    @GetMapping("/calender/delete/{id}")
    public String deletePatient(
            @PathVariable(name = "id") Long id,
            RedirectAttributes redirectAttributes) {
        try {
            calenderService.deleteCalender(id);
        } catch (Exception e) {
            throw new DeleteCalenderException(HttpStatus.BAD_REQUEST);
        }

        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Usunięto Termin "
        );
        return "redirect:/admin";
    }

}
