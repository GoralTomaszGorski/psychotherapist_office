package goral.psychotherapistoffice.domain.user;


import goral.psychotherapistoffice.config.security.TokenRepository;
import goral.psychotherapistoffice.domain.messeges.MessageService;
import goral.psychotherapistoffice.domain.messeges.dto.MessageDto;
import goral.psychotherapistoffice.domain.user.Dto.UserCredentialsDto;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.security.Principal;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    private static final String DEFAULT_USER_ROLE = "USER";
    private final UserRepository userRepository;
    private final UserRoleRepository userRoleRepository;
    private final PasswordEncoder passwordEncoder;
    private final MessageService messageService;
    private final TokenRepository tokenRepository;
    public UserService(UserRepository userRepository, UserRoleRepository userRoleRepository, PasswordEncoder passwordEncoder, MessageService messageService, TokenRepository tokenRepository) {
        this.userRepository = userRepository;
        this.userRoleRepository = userRoleRepository;
        this.passwordEncoder = passwordEncoder;
        this.messageService = messageService;
        this.tokenRepository = tokenRepository;
    }


    public Optional<UserCredentialsDto> findCredentialsByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(UserCredentialsDtoMapper::map);
    }


    @Transactional
    public void registerUserWithDefaultRole(
            UserRegistrationDto userRegistration) {
        UserRole defaultRole = userRoleRepository.findByName(DEFAULT_USER_ROLE).orElseThrow();

        User user = new User();
        user.setEmail(userRegistration
                .getEmail());
        user.setPassword(passwordEncoder
                .encode(userRegistration.getPassword()));
        user.getRoles().add(defaultRole);
        userRepository.save(user);
    }

    @Transactional
    public void saveUser(

            ){

    }

    public String sendEmailToken(User user, MessageDto messageDto) {
        try {
            String resetLink = generateResetToken(user);
            messageDto.setRecipient(messageDto.getRecipient());
            messageDto.setSubject("Reset hasła");
            messageDto.setBody(   "Hello \n\n" + "Please click on this link to Reset your Password :" + resetLink + ". \n\n"
                    + "Regards \n" + "ABC");
            messageService.sendMail(messageDto);
            return "success";
        } catch (Exception e) {
            e.printStackTrace();
            return "error";
        }

    }
    public String generateResetToken(User user) {
        UUID uuid = UUID.randomUUID();
        LocalDateTime currentDateTime = LocalDateTime.now();
        LocalDateTime expiryDateTime = currentDateTime.plusMinutes(30);
        ChangePasswordToken resetToken = new ChangePasswordToken();
        resetToken.setUser(user);
        resetToken.setToken(uuid.toString());
        resetToken.setExpiryDateTime(expiryDateTime);
        resetToken.setUser(user);
        ChangePasswordToken token = tokenRepository.save(resetToken);
        if (token != null) {
            String endpointUrl = "http://localhost:8080/resetPassword";
            return endpointUrl + "/" + resetToken.getToken();
        }
        return "/";
    }

//    @Transactional
//    public void changeCurrentUserPassword(
//            ChangePasswordToken request, Principal connectedUser) {
//        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();
//        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())){
//            throw new IllegalMonitorStateException(" Wrong password. Złe hasło");
//        }
//        if (!request.getNewPassword().equals(request.getConfirmationPassword())){
//            throw new IllegalMonitorStateException(" Password doesn't match. Hasło nie pasuje");
//        }
//        user.setPassword(passwordEncoder.encode(request.getNewPassword()));
//        userRepository.save(user);
//    }

    public String getCurrentUserName() {
        String currentUsername = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();;
        return currentUsername;
    }

    public boolean hasExipred(LocalDateTime expiryDateTime) {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return expiryDateTime.isAfter(currentDateTime);
    }
}