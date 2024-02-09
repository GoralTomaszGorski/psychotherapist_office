package goral.psychotherapistoffice.web;


import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class AppErrorController implements ErrorController {
    private final static Logger LOGGER = LoggerFactory.getLogger(AppErrorController.class);

    public String getErrorPath(){
        return "/error";
    }

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model){
        String pageTitle = "pageTitle";
        String errorInfo = "errorInfo";

        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);

        if (status != null){
            Integer statusCode = Integer.valueOf(status.toString());
            if (statusCode == HttpStatus.NOT_FOUND.value()){
                pageTitle = "Strona nie istnieje (error 404)";
                errorInfo = "Strona, którą próbujesz odwiedzić nie została odnaleziona lub termin który próbujesz zarezerwować jest już zajęty";
                LOGGER.error("Error 404");
            } else if (statusCode == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
                pageTitle = "Błąd - wewnętrzny błąd serwera (error 500)";
                errorInfo = "Wystąpił błąd wewnętrzny serwera - przepraszamy";
                LOGGER.error("Error 500");
            } else if (statusCode == HttpStatus.FORBIDDEN.value()) {
                pageTitle = "Brak dostępu (error 403)";
                errorInfo = "Nie masz uprawnień do strony, którą próbujesz odwiedzić.";
                LOGGER.error("Error 403");

            }
        }
        model.addAttribute("pageTitle", pageTitle);
        model.addAttribute("errorInfo", errorInfo);
        return "error/error";
    }
}
