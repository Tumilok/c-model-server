package pl.edu.agh.racing.cmodel.service;

import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.agh.racing.cmodel.dto.RegisterRequest;
import pl.edu.agh.racing.cmodel.exception.CModelException;
import pl.edu.agh.racing.cmodel.model.NotificationEmail;
import pl.edu.agh.racing.cmodel.model.Role;
import pl.edu.agh.racing.cmodel.model.User;
import pl.edu.agh.racing.cmodel.model.VerificationToken;
import pl.edu.agh.racing.cmodel.repository.UserRepository;
import pl.edu.agh.racing.cmodel.repository.VerificationTokenRepository;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .enabled(false)
                .role(Role.NEWBIE)
                .build();

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmail("Please Activate Your Account",
                user.getEmail(), "http://localhost:8081/api/auth/accountVerification/" + token));
    }

    private String generateVerificationToken(User user) {
        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken();
        verificationToken.setToken(token);
        verificationToken.setUser(user);
        verificationToken.setExpiryDate(Instant.now().plus(Duration.ofDays(7)));

        verificationTokenRepository.save(verificationToken);
        return token;
    }

    public void verifyAccount(String token) {
        Optional<VerificationToken> verificationToken = verificationTokenRepository.findByToken(token);
        verificationToken.orElseThrow(() -> new CModelException("Invalid Verification Token"));
        fetchUserAndEnable(verificationToken.get());
    }

    @Transactional
    void fetchUserAndEnable(VerificationToken verificationToken) {
        String email = verificationToken.getUser().getEmail();
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new CModelException("User not found with email: " + email));
        user.setEnabled(true);
        userRepository.save(user);
    }
}
