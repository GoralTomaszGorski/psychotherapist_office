package goral.psychotherapistoffice.domain.patient;

import goral.psychotherapistoffice.domain.patient.credentials.PatientRole;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;

import java.util.Set;
import java.util.stream.Collectors;

public class PatientDtoMapper {

    static PatientDto map(Patient patient){
        return new PatientDto(
                patient.getId(),
                patient.getNick(),
                patient.getName(),
                patient.getSurname(),
                patient.getTelephone(),
                patient.getYearOfBrith(),
                patient.getEmail(),
                patient.getPassword(),
                patient.getRoles()
                        .stream().map(PatientRole::getName)
                        .collect(Collectors.toSet()));
    }
}
