package goral.psychotherapistoffice.domain.calender;

import goral.psychotherapistoffice.domain.calender.dto.CalenderDto;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CalenderRepository extends CrudRepository <Calender, Long>{

    List<Calender> findAll();

    List<Calender> findAllByFreeIsTrueOrderById();
    List<Calender> findAllByFreeIsTrue();

    Optional<Calender> findCalenderByIdAndFreeIsTrue(long id);

    CalenderDto findById(long id);
    
}
