package goral.psychotherapistoffice.domain.patient;

import goral.psychotherapistoffice.domain.patient.credentials.*;
import goral.psychotherapistoffice.domain.patient.dto.PatientDto;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    public final PatientRepository patientRepository;

/*
    ustwienia Roli
*/
    /*public final PatientCredentialsDto patientCredentialsDto;*/
    private final PatientRoleRepository patientRoleRepository;
    private static final String DEFAULT_PATIENT_ROLE = "PATIENT";
    private final PasswordEncoder passwordEncoder;


    public PatientService(PatientRepository patientRepository, PatientRoleRepository patientRoleRepository, /*PatientCredentialsDto patientCredentialsDto,*/ PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.patientRoleRepository = patientRoleRepository;
/*
        this.patientCredentialsDto = patientCredentialsDto;
*/
        this.passwordEncoder = passwordEncoder;
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
        PatientRole defaultRole = patientRoleRepository.findByName(DEFAULT_PATIENT_ROLE).orElseThrow();

        Patient patientToSave = new Patient();
        patientToSave.setNick(patientDto.getNick());
        patientToSave.setName(patientDto.getName());
        patientToSave.setSurname(patientDto.getSurname());
        patientToSave.setTelephone(patientDto.getTelephone());
        patientToSave.setYearOfBrith(patientDto.getYearOfBrith());
        patientToSave.setEmail(patientDto.getEmail());
        patientToSave.setPassword(passwordEncoder.encode(patientDto.getPassword()));
        patientToSave.getRoles().add(defaultRole);
        patientRepository.save(patientToSave);
    }

    //credentials
    public Optional<PatientCredentialsDto> findCredentialsByEmail(String email) {
        return patientRepository.findByEmail(email)
            .map(PatientCredentialsDtoMapper::map);
        }
    }



