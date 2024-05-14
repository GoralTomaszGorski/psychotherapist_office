package goral.psychotherapistoffice.domain.patient;


import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@Service
public class DateInWarsaw {

    public ZonedDateTime localDateInWarsaw;

    public ZonedDateTime getLocalDateInWarsaw() {
        return localDateInWarsaw =
                ZonedDateTime.of(LocalDateTime.now(),
                ZoneId.of("Europe/Warsaw"));
    }
}












