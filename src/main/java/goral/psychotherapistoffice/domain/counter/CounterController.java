//package goral.psychotherapistoffice.domain.counter;
//
//
//import jakarta.servlet.http.HttpServletRequest;
//import org.springframework.stereotype.Controller;
//import org.springframework.web.bind.annotation.GetMapping;
//
//@Controller
//public class CounterController {
//
//    private final CounterService counterService;
//
//    public CounterController(CounterService counterService) {
//        this.counterService = counterService;
//    }
//
//
//    @GetMapping("/")
//    public String home(HttpServletRequest request) {
//        String sessionId = request.getSession().getId();
//        String ip = request.getRemoteAddr();
//        Counter counter = counterService.findBySessionAndIp(sessionId, ip);
//        if (counter == null) {
//            counterService.incrementEntry(sessionId, ip);
//        } else {
//            counterService.incrementRefresh(sessionId, ip);
//        }
//        return "layout"; // Zwróć nazwę szablonu Thymeleaf
//    }
//}
//
