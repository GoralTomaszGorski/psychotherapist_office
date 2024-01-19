package goral.psychotherapistoffice.domain.patient;

import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    public final PatientRepository patientRepository;

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }


    public Optional<PatientDto> findByNickIgnoreCase(String nick){
        return patientRepository.findByNickIgnoreCase(nick).map(PatientDtoMapper::map);
    }
    public Optional<PatientDto> findPatientById(long id){
        return patientRepository.findPatientById(id).map(PatientDtoMapper::map);
    }


    public List<PatientDto>findAllPatients(){
        return patientRepository.findAll()
                .stream()
                .map(PatientDtoMapper::map).toList();
    }


    @Transactional
    public void addPatient(PatientDto patientDto){
        Patient patientToSave = new Patient();
        patientToSave.setNick(patientDto.getNick());
        patientToSave.setName(patientDto.getName());
        patientToSave.setSurname(patientDto.getSurname());
        patientToSave.setTelephone(patientDto.getTelephone());
        patientToSave.setYearOfBrith(patientDto.getYearOfBrith());
        patientRepository.save(patientToSave);

    }

}
