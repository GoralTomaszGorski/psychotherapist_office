package goral.psychotherapistoffice.domain.counter;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface CounterRepository extends JpaRepository<Counter, Long> {

    int countByUrl(String url);

    Counter findBySessionIdAndUrl(String sessionId, String url);

}
