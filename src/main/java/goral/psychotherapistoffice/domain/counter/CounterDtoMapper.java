package goral.psychotherapistoffice.domain.counter;

import goral.psychotherapistoffice.domain.counter.dto.CounterDto;

public class CounterDtoMapper {
    static CounterDto map(Counter counter){
        return new CounterDto(
                counter.getId(),
                counter.getSessionId(),
                counter.getIp(),
                counter.getRefresh(),
                counter.getEntry(),
                counter.getDate()
        );
    }
}
