package goral.psychotherapistoffice.domain.counter;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.annotation.WebInitParam;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;


@WebFilter(filterName = "TimeOfDayFilter",
        urlPatterns = {"/*"},
        initParams = {
                @WebInitParam(name = "mood", value = "awake")})

public class UrlVisitFilter implements Filter {

    private final CounterService counterService;

    public UrlVisitFilter(CounterService counterService) {
        this.counterService = counterService;
    }


    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
                         FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String url = httpRequest.getRequestURI(); // Pobierz adres URL żądania
        String sessionId = httpRequest.getSession().getId(); // Pobierz sessionId z sesji
        String ip = httpRequest.getRemoteAddr(); // Pobierz adres IP klienta

        // Zaktualizuj licznik wizyt dla danego adresu URL
        counterService.incrementVisitCount(url, sessionId, ip);
        chain.doFilter(request, response);
    }

}
