package goral.psychotherapistoffice.config.security;

import goral.psychotherapistoffice.domain.user.ChangePasswordToken;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TokenRepository extends JpaRepository<ChangePasswordToken, Integer> {

    ChangePasswordToken findByToken(String token);
}
