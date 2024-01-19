package goral.psychotherapistoffice.domain.calender;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CalenderRepository extends CrudRepository <Calender, Long>{


    List<Calender> findAll();

    List<Calender> findAllByFreeIsTrueOrderById();
    
    Optional<Calender> findCalenderByIdAndFreeIsTrue(long id);


}
