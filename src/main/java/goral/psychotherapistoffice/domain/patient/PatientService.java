package goral.psychotherapistoffice.domain.patient;

import goral.psychotherapistoffice.domain.exception.DeletePatientException;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;

import goral.psychotherapistoffice.domain.user.UserService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {



    private final PatientJpaRepository patientJpaRepository;
    private final UserService userService;


    public PatientService(PatientJpaRepository patientJpaRepository, UserService userService) {
        this.patientJpaRepository = patientJpaRepository;
        this.userService = userService;
    }

    public Optional<PatientDto> findPatientById(long id){
        return patientJpaRepository.findPatientById(id)
                .map(PatientDtoMapper::map);
    }

    public List <PatientDto> findBySurnameOrName(String keyword){
        return patientJpaRepository.findBySurnameContainsIgnoreCaseOrNameContainsIgnoreCase(keyword, keyword)
                .stream()
                .map(PatientDtoMapper::map)
                .toList();
    }


    public List<PatientDto>findAllPatients(){
        return patientJpaRepository.findAll()
                .stream()
                .map(PatientDtoMapper::map).toList();
    }

    @Transactional
    public Patient addPatient(PatientDto patientDto){
        Patient patientToSave = new Patient();
        patientToSave.setNick(patientDto.getNick());
        patientToSave.setName(patientDto.getName());
        patientToSave.setSurname(patientDto.getSurname());
        patientToSave.setTelephone(patientDto.getTelephone());
        if (patientDto.getEmail().isEmpty()) {
            patientToSave.setEmail(userService.getCurrentUserName());
        }else {
            patientToSave.setEmail(patientDto.getEmail());
        }
        patientToSave.setYearOfBrith(patientDto.getYearOfBrith());
        patientJpaRepository.save(patientToSave);
        return patientToSave;
    }

    public void deletePatient(Long id) {
        try {
            patientJpaRepository.deletePatientById(id);
        } catch (Throwable e) {
            throw new DeletePatientException();
        }
    }

}


