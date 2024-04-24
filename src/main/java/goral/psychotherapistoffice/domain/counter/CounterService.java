package goral.psychotherapistoffice.domain.counter;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;


@Service
public class CounterService {


    private final CounterRepository counterRepository;

    public CounterService(CounterRepository counterRepository) {
        this.counterRepository = counterRepository;
    }

    @Transactional
    public void incrementVisitCount(String sessionId, String ip, String url) {
        Counter counter = counterRepository.findBySessionIdAndIp(sessionId, ip);
        if (counter == null) {
            counter = new Counter();
            counter.setSessionId(sessionId);
            counter.setIp(ip);
            counter.setEntry(1);
            counter.setRefresh(0);
            counter.setUrl(url);
        } else {
            counter.setEntry(counter.getEntry() + 1);
        }
        counter.setDate(new Date());
        counterRepository.save(counter);
    }

    @Transactional
    public void incrementRefresh(String sessionId, String ip, String url) {
        Counter counter = counterRepository.findBySessionIdAndIp(sessionId, ip);
        if (counter == null) {
            counter = new Counter();
            counter.setSessionId(sessionId);
            counter.setIp(ip);
            counter.setEntry(0);
            counter.setRefresh(1);
            counter.setUrl(url);
        } else {
            counter.setRefresh(counter.getRefresh() + 1);
        }
        counter.setDate(new Date());
        counterRepository.save(counter);
    }

    public int getVisitCount(String url) {
        Counter counter = counterRepository.findByUrl(url);
        return (counter != null) ? counter.getEntry() : 0;
    }

    public int getRefreshCount(String url, String sessionId, String ip) {
        Counter counter = counterRepository.findBySessionIdAndIp(sessionId, ip);
        return (counter != null) ? counter.getRefresh() : 0;
    }
}
