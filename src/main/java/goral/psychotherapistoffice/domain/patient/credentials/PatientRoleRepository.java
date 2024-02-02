package goral.psychotherapistoffice.domain.patient.credentials;

import org.springframework.data.repository.CrudRepository;

import java.util.Optional;



/*
szukamy po nazwie roli
*/
public interface PatientRoleRepository extends CrudRepository<PatientRole, Long> {
    Optional<PatientRole> findByName(String name);

}
