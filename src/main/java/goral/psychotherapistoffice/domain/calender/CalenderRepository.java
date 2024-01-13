package goral.psychotherapistoffice.domain.calender;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CalenderRepository extends CrudRepository <Calender, Long>{


    List<Calender> findAll();

    List<Calender> findAllByFreeIsTrueOrderById();


}
