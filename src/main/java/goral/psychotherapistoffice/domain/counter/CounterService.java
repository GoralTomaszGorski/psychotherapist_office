package goral.psychotherapistoffice.domain.counter;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;



@Service
public class CounterService {


    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Transactional
    public void httpParameterToIncrement(HttpServletRequest request){
        String sessionId = request.getSession().getId();
        String ip = request.getRemoteAddr();
        String url = request.getRequestURI();
        incrementCounter(sessionId, ip, url);
    }


    public void incrementCounter(String sessionId, String ip, String url) {
        Counter counter = counterRepository.findBySessionIdAndUrl(sessionId, url);
        if (counter == null) {
            counter = new Counter();
            counter.setSessionId(sessionId);
            counter.setIp(ip);
            counter.setUrl(url);
            counter.setEntry(1);
            counter.setDate(LocalDate.now());
        } else {
            counter.setRefresh(counter.getRefresh() + 1);
        }
        counterRepository.save(counter);
    }

    public int getCountForUrl(String url) {
        return counterRepository.countByUrl(url);
    }
}
