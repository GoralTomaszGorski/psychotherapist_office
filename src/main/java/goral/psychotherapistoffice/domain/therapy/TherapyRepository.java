package goral.psychotherapistoffice.domain.therapy;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TherapyRepository extends CrudRepository<Therapy, Long> {
    List<Therapy>findAll();


    Optional<Therapy>findById(Long id);

/*
    Optional<Therapy> findTherapyByIdString(String id);
*/

    Optional<Therapy> findByKindOfTherapyIgnoreCase(String kindOfTherapy);



}
