package goral.psychotherapistoffice.domain.patient;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends CrudRepository<Patient, Long> {

    List<Patient>findAll();

    Optional<Patient> findPatientById(long id);
    Optional<Patient> findPatientByNick(String nick);


}
