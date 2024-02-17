package goral.psychotherapistoffice.domain.patient;

import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import org.apache.commons.collections4.iterators.CollatingIterator;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    private PatientRepository patientRepository;
    private PatientJpaRepository patientJpaRepository;


    public PatientService(PatientRepository patientRepository, PatientJpaRepository patientJpaRepository) {
        this.patientRepository = patientRepository;
        this.patientJpaRepository = patientJpaRepository;
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
    public void addPatient(PatientDto patientDto){
        Patient patientToSave = new Patient();
        patientToSave.setNick(patientDto.getNick());
        patientToSave.setName(patientDto.getName());
        patientToSave.setSurname(patientDto.getSurname());
        patientToSave.setTelephone(patientDto.getTelephone());
        patientToSave.setYearOfBrith(patientDto.getYearOfBrith());
        patientJpaRepository.save(patientToSave);
    }

    @Transactional
    public void deletePatient(Long id) {
        try {
            patientJpaRepository.deletePatientById(id);
        } catch (EmptyResultDataAccessException e){
            // ignore
        }
    }
}


