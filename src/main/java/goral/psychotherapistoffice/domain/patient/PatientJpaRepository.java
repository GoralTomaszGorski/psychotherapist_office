package goral.psychotherapistoffice.domain.patient;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;


public interface PatientJpaRepository extends JpaRepository<Patient, Long> {
    Optional<Patient> findPatientById(long id);

    List<Patient>findAll();

    List<Patient> findBySurnameContainsIgnoreCaseOrNameContainsIgnoreCase(String surname, String name);

    void deletePatientById(long id);

}
