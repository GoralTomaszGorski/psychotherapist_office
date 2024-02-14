package goral.psychotherapistoffice.domain.meeting;

import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;

public class MeetingDtoMapper {
    static MeetingDto map(Meeting meeting){
        return new MeetingDto(
                meeting.getPatient(),
                meeting.getTherapy(),
                meeting.getCalender()
        );
    }
}
