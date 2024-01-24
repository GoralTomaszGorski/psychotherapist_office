package goral.psychotherapistoffice.domain.patient;

import java.util.Set;
import java.util.stream.Collectors;

public class PatientCredentialsDtoMapper {
    static PatientCredentialsDto map(Patient patient){
        String email = patient.getEmail();
        String password = patient.getPassword();
        Set<String> roles = patient.getRoles()
                .stream()
                .map(PatientRole::getName)
                .collect(Collectors.toSet());
        return new PatientCredentialsDto(email, password, roles);
    }
}
