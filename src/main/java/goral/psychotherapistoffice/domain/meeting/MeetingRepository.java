package goral.psychotherapistoffice.domain.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository <Meeting, Long>{


    List<Meeting>findAll();
    List<Meeting>findAllByCalenderIsNotNullOrderByCalender();

    Optional<Meeting> findMeetingById(long id);
    List<Meeting> findMeetingByPatientEmail(String email);

    //    @Query(value = "SELECT * FROM shop s WHERE s.owner_name LIKE %:keyword% OR s.shop_type LIKE %:keyword%",
//                        nativeQuery = true)
//    List<Meeting> findByKeyword(@Param("keyword") String keyword);

    void deleteMeetingById(long id);




}
