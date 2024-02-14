package goral.psychotherapistoffice.domain.patient;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {

    List<Patient>findAll();

    Optional<Patient> findPatientById(long id);
    List<Patient> findPatientByNickOrSurnameOrName(String nick, String surname, String name);

    List<Patient> findPatientByNick(String nick);
    List<Patient> findPatientBySurname(String surname);
    List<Patient> findPatientByName(String nick);

}
