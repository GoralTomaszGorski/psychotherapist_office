package goral.psychotherapistoffice.domain.patient;


import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long> {

//    List<Patient>findAll();

    Optional<Patient> findPatientById(long id);

}
