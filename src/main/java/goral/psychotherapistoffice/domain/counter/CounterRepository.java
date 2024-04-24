package goral.psychotherapistoffice.domain.counter;

import org.springframework.data.jpa.repository.JpaRepository;


public interface CounterRepository extends JpaRepository<Counter, Long> {

    Counter findBySessionIdAndIp(String sessionId, String ip);
    Counter findByUrl(String url);
    Counter findBySessionId(String id);
}
