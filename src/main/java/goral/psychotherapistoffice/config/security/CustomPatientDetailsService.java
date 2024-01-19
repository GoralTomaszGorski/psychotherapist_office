package goral.psychotherapistoffice.config.security;

import goral.psychotherapistoffice.domain.patient.PatientCredentialsDto;
import goral.psychotherapistoffice.domain.patient.PatientService;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
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
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return patientService.findCredentialsByEmail(username)
                .map(this::createUserDetails)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("User with email %s not found", username)));
    }

    private UserDetails createUserDetails(PatientCredentialsDto patientCredentialsDto){
        return User.builder()
                .username(patientCredentialsDto.getEmail())
                .password(patientCredentialsDto.getPassword())
                .roles(patientCredentialsDto.getRoles().toArray(String[]::new))
                .build();
    }
}
