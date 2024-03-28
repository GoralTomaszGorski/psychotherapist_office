package goral.psychotherapistoffice.web.admin;


import goral.psychotherapistoffice.domain.calender.CalenderService;
import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import goral.psychotherapistoffice.domain.exception.CalenderNotFoundException;
import goral.psychotherapistoffice.domain.exception.DeleteCalenderException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

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
        model.addAttribute("heading",
                "Podaj dane nowego Terminu w kalendarzu");
        model.addAttribute("description",
                "Dodaj informacje dotyczące nowego teminu spotkań dostępnego pacjentów");
        model.addAttribute("admin/dodaj-termin",
                "admin/dodaj-termin");
        return "admin/calender-add-form";
    }

    @PostMapping("/dodaj-termin")
    public String addCalender(@ModelAttribute("calender")
                                  CalenderDto calenderDto, RedirectAttributes redirectAttributes) {
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
        model.addAttribute("heading",
                "Edytuj dane Terminu w kalendarzu");
        model.addAttribute("description",
                "Edytuj informacje dotyczące teminu spotkań dostępnego pacjentów");
        return "admin/calender-therms-view";
    }

    @GetMapping("/calender/edit/{id}")
    public String editCalender(Model model,
                               @PathVariable(name = "id") Long id) throws CalenderNotFoundException {
        CalenderDto calenderDto = calenderService.findCalenderById(id)
                .orElseThrow();
        model.addAttribute("calenderDto", calenderDto);
        model.addAttribute("heading",
                "Edytuj dane Terminu w kalendarzu");
        model.addAttribute("description",
                "Edytuj informacje dotyczące teminu spotkań dostępnego pacjentów");
        model.addAttribute("calender/edit",
                "calender/edit");
        return "admin/calender-edit-form";
    }

    @PostMapping("/calender/edit")
    public String editCalender(
            CalenderDto calenderDto, RedirectAttributes redirectAttributes) {
        calenderService.editCalender(calenderDto);
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

    @GetMapping("/calender/delete/{id}")
    public String deletePatient(
            @PathVariable(name = "id") Long id,
            RedirectAttributes redirectAttributes) {
        try {
            calenderService.deleteCalender(id);
        } catch (Exception e) {
            throw new DeleteCalenderException();
        }
        redirectAttributes.addFlashAttribute(
                AdminController.NOTIFICATION_ATTRIBUTE,
                "Usunięto Termin "
        );
        return "redirect:/admin";
    }

}
