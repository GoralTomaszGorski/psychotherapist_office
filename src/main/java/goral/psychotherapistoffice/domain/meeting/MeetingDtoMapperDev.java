package goral.psychotherapistoffice.domain.meeting;

import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;
import goral.psychotherapistoffice.domain.meeting.dto.MeetingDtoDev;

public class MeetingDtoMapperDev {
    static MeetingDtoDev map(Meeting meeting){
        return new MeetingDtoDev(
                meeting.getId(),
                meeting.getPatient(),
                meeting.getPatient().getName(),
                meeting.getPatient().getSurname(),
                meeting.getCalender(),
                meeting.getCalender().getTime(),
                meeting.getCalender().getDayof()

        );
    }
}
