package goral.psychotherapistoffice.domain.meeting;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface MeetingRepository extends JpaRepository <Meeting, Long>{


    List<Meeting>findAll();
    List<Meeting>findAllByCalenderIsNotNullOrderByCalender();
    List<Meeting>findAllByCalenderIsNotNullOrderById();

    Optional<Meeting> findMeetingById(long id);
    List<Meeting> findMeetingByPatientEmail(String email);
    List<Meeting> searchAllByCalender_DayofOrPatient_SurnameOrPatient_Name(String day, String surname, String name);
    List<Meeting> findMeetingsByCalenderDayofContainsIgnoreCaseOrPatientSurnameContainsIgnoreCaseOrPatientNameContainsIgnoreCaseOrderByCalender(String day, String surname, String name);



    @Query(value = "SELECT * FROM MEETING m, PATIENT p, CALENDER c  where c.ID =PATIENT_ID and CALENDER_ID =c.ID and surname LIKE %:keyword% ",
                        nativeQuery = true)
    List<Meeting> findByKeyword(@Param("keyword") String keyword);

    void deleteMeetingById(long id);




}
