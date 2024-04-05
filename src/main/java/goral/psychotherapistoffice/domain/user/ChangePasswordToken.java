package goral.psychotherapistoffice.domain.user;

import jakarta.persistence.*;

import java.time.LocalDateTime;


@Entity
@Table(name = "change_password_token")
public class ChangePasswordToken {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    private String token;
    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
    private LocalDateTime expiryDateTime;
    @OneToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "id", nullable = false)
    private User user;

    public ChangePasswordToken() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCurrentPassword() {
        return currentPassword;
    }

    public void setCurrentPassword(String currentPassword) {
        this.currentPassword = currentPassword;
    }

    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public String getConfirmationPassword() {
        return confirmationPassword;
    }

    public void setConfirmationPassword(String confirmationPassword) {
        this.confirmationPassword = confirmationPassword;
    }

    public LocalDateTime getExpiryDateTime() {
        return expiryDateTime;
    }

    public void setExpiryDateTime(LocalDateTime expiryDateTime) {
        this.expiryDateTime = expiryDateTime;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public ChangePasswordToken(Long id, String token, String currentPassword, String newPassword, String confirmationPassword, LocalDateTime expiryDateTime, User user) {
        this.id = id;
        this.token = token;
        this.currentPassword = currentPassword;
        this.newPassword = newPassword;
        this.confirmationPassword = confirmationPassword;
        this.expiryDateTime = expiryDateTime;
        this.user = user;
    }
}
