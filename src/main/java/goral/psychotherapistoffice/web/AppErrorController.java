//package goral.psychotherapistoffice.web;
//
//import goral.psychotherapistoffice.domain.exception.UserMessageExceptionInterface;
//import jakarta.servlet.RequestDispatcher;
//import jakarta.servlet.http.HttpServletRequest;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.web.servlet.error.ErrorAttributes;
//import org.springframework.boot.web.servlet.error.ErrorController;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.context.request.WebRequest;
//
//@Controller
//public class AppErrorController implements ErrorController {
//    private final static Logger LOGGER = LoggerFactory.getLogger(AppErrorController.class);
//    private final ErrorAttributes errorAttributes;
//
//    @Autowired
//    public AppErrorController(ErrorAttributes errorAttributes) {
//        this.errorAttributes = errorAttributes;
//    }
//
//    public String getErrorPath() {
//        return "/error";
//    }
//
//    @RequestMapping("/error")
//    public String handleError(
//            HttpServletRequest request, WebRequest webRequest, Model model) {
//        Object status = request.getAttribute(
//                RequestDispatcher.ERROR_STATUS_CODE);
//        Throwable throwable = this.errorAttributes.getError(webRequest);
//        String pageTitle = this.getTitleForStatus(
//                Integer.valueOf(status.toString()));
//        String errorInfo = "Przepraszamy, wystąpił nieznany błąd.";
//        if (throwable instanceof UserMessageExceptionInterface) {
//            errorInfo = ((UserMessageExceptionInterface) throwable).getUserMessage();
//        }
//        LOGGER.error("Http error " + status + ": " + errorInfo);
//        model.addAttribute("pageTitle", pageTitle);
//        model.addAttribute("errorInfo", errorInfo);
//        return "error/error";
//    }
//
//    private String getTitleForStatus(int status) {
//        if (status == HttpStatus.BAD_REQUEST.value()) {
//            return "Błędne żądanie.";
//        }
//        if (status == HttpStatus.FORBIDDEN.value()) {
//            return "Nie masz uprawnień do strony, którą próbujesz odwiedzić.";
//        }
//        if (status == HttpStatus.NOT_FOUND.value()) {
//            return "Strona nie istnieje.";
//        }
//        if (status == HttpStatus.INTERNAL_SERVER_ERROR.value()) {
//            return "Wystąpił błąd wewnętrzny serwera - przepraszamy.";
//        }
//        return "Nieznany kod błędu.";
//    }
//}
