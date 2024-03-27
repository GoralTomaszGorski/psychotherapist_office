package goral.psychotherapistoffice.domain.meeting;

import goral.psychotherapistoffice.domain.meeting.dto.MeetingDto;

public class MeetingDtoMapper {
    static MeetingDto map(Meeting meeting){
        return new MeetingDto(
                meeting.getId(),
                meeting.getPatient().getNick(),
                meeting.getPatient().getName(),
                meeting.getPatient().getSurname(),
                meeting.getPatient().getTelephone(),
                meeting.getTherapy().getKindOfTherapy(),
                meeting.getCalender().getDayof(),
                meeting.getCalender().getTime()
        );
    }
}
