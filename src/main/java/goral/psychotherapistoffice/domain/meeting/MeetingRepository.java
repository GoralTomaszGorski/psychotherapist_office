package goral.psychotherapistoffice.domain.meeting;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface MeetingRepository extends CrudRepository <Meeting, Long>{


    List<Meeting> findAllByCalenderIsNotNullOrderByCalender();


}
