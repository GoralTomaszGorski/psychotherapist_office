package goral.psychotherapistoffice.domain.meeting;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository <Meeting, Long>{

    List<Meeting>findAllByCalenderIsNotNullOrderByCalender();

    Optional<Meeting> findMeetingById(long id);

    List<Meeting> findMeetingByPatientEmail(String email);

    List<Meeting> findMeetingsByCalenderDayofContainsIgnoreCaseOrPatientSurnameContainsIgnoreCaseOrPatientNameContainsIgnoreCaseOrderByCalender
            (String day, String surname, String name);

    void deleteMeetingById(long id);

}
