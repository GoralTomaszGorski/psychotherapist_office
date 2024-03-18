package goral.psychotherapistoffice.domain.meeting;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends CrudRepository <Meeting, Long>{


    List<Meeting>findAll();
    List<Meeting>findAllByCalenderIsNotNullOrderByCalender();

    Optional<Meeting> findMeetingById(long id);
    List<Meeting> findMeetingByPatientEmail(String email);

    void deleteMeetingById(long id);




}
