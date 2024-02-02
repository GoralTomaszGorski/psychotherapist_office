package goral.psychotherapistoffice.config.security;

import goral.psychotherapistoffice.domain.patient.credentials.PatientCredentialsDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
class CustomPatientDetailsService implements UserDetailsService {

    private final PatientService patientService;

    public CustomPatientDetailsService(PatientService patientService) {
        this.patientService = patientService;
    }

    @Override
    public UserDetails loadUserByUsername(String patientEmail) throws UsernameNotFoundException {
        return patientService.findCredentialsByEmail(patientEmail)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email or nick %s not found", patientEmail)));
    }

    private UserDetails createUserDetails(PatientCredentialsDto patientCredentialsDto){
        return User.builder()
                .username(patientCredentialsDto.getEmail())
                .password(patientCredentialsDto.getPassword())
                .roles(patientCredentialsDto.getRoles().toArray(String[]::new))
                .build();
    }
}
