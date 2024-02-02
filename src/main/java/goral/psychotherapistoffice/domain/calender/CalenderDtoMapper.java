package goral.psychotherapistoffice.domain.calender;

import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;

public class CalenderDtoMapper {
    static CalenderDto map(Calender calender){
        return new CalenderDto(
                calender.getId(),
                calender.getDayof(),
                calender.getTime(),
                calender.isFree()
        );
    }
}
