package goral.psychotherapistoffice.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ChangePasswordTokenRepository extends JpaRepository<ChangePasswordToken, Integer> {
    ChangePasswordToken findByToken(String token);
}
