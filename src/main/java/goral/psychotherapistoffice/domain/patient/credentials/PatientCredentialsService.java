package goral.psychotherapistoffice.domain.patient.credentials;

import goral.psychotherapistoffice.domain.patient.PatientRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PatientCredentialsService {
    private static final String DEFAULT_PATIENT_ROLE = "PATIENT";
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientCredentialsService(PatientRepository patientRepository, PasswordEncoder passwordEncoder) {
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }




}
