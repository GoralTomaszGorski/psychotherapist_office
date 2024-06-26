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
    private final DateInWarsaw dateInWarsaw;


    public PatientService(PatientJpaRepository patientJpaRepository, UserService userService, DateInWarsaw dateInWarsaw) {
        this.patientJpaRepository = patientJpaRepository;
        this.userService = userService;
        this.dateInWarsaw = dateInWarsaw;
    }

    public Optional<PatientDto> findPatientById(long id) {
        return patientJpaRepository.findPatientById(id)
                .map(PatientDtoMapper::map);
    }

    public List<PatientDto> findPatientByEmail(String email) {
        return patientJpaRepository
                .findPatientByEmail(email)
                .stream()
                .map(PatientDtoMapper::map)
                .toList();
    }

    public List<PatientDto> findBySurnameOrName(String keyword) {
        return patientJpaRepository
                .findBySurnameContainsIgnoreCaseOrNameContainsIgnoreCase(keyword, keyword)
                .stream()
                .map(PatientDtoMapper::map)
                .toList();
    }

    public List<PatientDto> findAllPatients() {
        return patientJpaRepository.findAllBy()
                .stream()
                .map(PatientDtoMapper::map)
                .toList();
    }

    @Transactional
    public Patient addPatient(PatientDto patientDto) {
        Patient patientToSave = new Patient();
        if (patientDto.getNick().isEmpty()) {
            setNewNick(patientDto, patientToSave);
        } else {
            patientToSave.setNick(patientDto.getNick());
        }
        patientToSave.setName(patientDto.getName());
        patientToSave.setSurname(patientDto.getSurname());
        patientToSave.setTelephone(patientDto.getTelephone());
        patientToSave.setEmail(userService.getCurrentUserName());
        patientToSave.setYearOfBrith(patientDto.getYearOfBrith());
        patientToSave.setJoinDate(dateInWarsaw.getLocalDateInWarsaw());
        patientToSave.setInformation(patientDto.getInformation());
        patientToSave.setApproval(true);
        patientJpaRepository.save(patientToSave);
        return patientToSave;
    }

    private static void setNewNick(PatientDto patientDto, Patient patientToSave) {
        StringBuilder sB = new StringBuilder();
        String tel = patientDto.getTelephone();
        int n = tel.length();
        for (int i = (n - 3);
             i < n;
             i++) {
            char c = patientDto.getTelephone().charAt(i);
            sB.append(c);
        }
        sB.append(patientDto.getName().charAt(0));
        sB.append(patientDto.getSurname().charAt(0));
        sB.append(patientDto.getSurname().charAt(1));
        patientToSave.setNick(String.valueOf(sB));
    }

    @Transactional
    public void deletePatient(Long id) {
        try {
            patientJpaRepository.deletePatientById(id);
        } catch (Throwable e) {
            throw new DeletePatientException();
        }
    }

}




