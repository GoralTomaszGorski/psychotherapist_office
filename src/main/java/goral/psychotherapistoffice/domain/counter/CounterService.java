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

    public Counter findBySessionAndIp(String sessionId, String ip) {
        return counterRepository
                .findBySessionIdAndIp(sessionId, ip);
    };

    @Transactional
    public void incrementEntry(String sessionId, String ip) {
        Counter counter = counterRepository.findBySessionIdAndIp(sessionId, ip);
        if (counter == null) {
            counter = new Counter();
            counter.setSessionId(sessionId);
            counter.setIp(ip);
            counter.setEntry(1);
            counter.setRefresh(0);
        } else {
            counter.setEntry(counter.getEntry() + 1);
        }
        counter.setDate(new Date());
        counterRepository.save(counter);
    }

    @Transactional
    public void incrementRefresh(String sessionId, String ip) {
    Counter counter = counterRepository.findBySessionIdAndIp(sessionId, ip);
    if (counter == null) {
        counter = new Counter();
        counter.setSessionId(sessionId);
        counter.setIp(ip);
        counter.setEntry(0);
        counter.setRefresh(1);
    } else {
        counter.setRefresh(counter.getRefresh() + 1);
    }
    counter.setDate(new Date());
    counterRepository.save(counter);    }

}
