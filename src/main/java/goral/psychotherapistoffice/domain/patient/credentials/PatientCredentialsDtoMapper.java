package goral.psychotherapistoffice.domain.patient.credentials;

import goral.psychotherapistoffice.domain.patient.Patient;

import java.util.Set;
import java.util.stream.Collectors;

public class PatientCredentialsDtoMapper {
    public static PatientCredentialsDto map(Patient patient){
        String nick = patient.getNick();
        String email = patient.getEmail();
        String password = patient.getPassword();
        Set<String> roles = patient.getRoles()
                .stream()
                .map(PatientRole::getName)
                .collect(Collectors.toSet());
        return new PatientCredentialsDto(nick, email, password, roles);
    }
}
