package pl.edu.agh.racing.cmodel.service;

import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import pl.edu.agh.racing.cmodel.dto.NotificationEmailDto;
import pl.edu.agh.racing.cmodel.dto.response.AuthenticationResponse;
import pl.edu.agh.racing.cmodel.dto.request.LoginRequest;
import pl.edu.agh.racing.cmodel.dto.request.RegisterRequest;
import pl.edu.agh.racing.cmodel.exception.CModelException;
import pl.edu.agh.racing.cmodel.model.*;
import pl.edu.agh.racing.cmodel.repository.RoleRepository;
import pl.edu.agh.racing.cmodel.repository.UserRepository;
import pl.edu.agh.racing.cmodel.repository.VerificationTokenRepository;
import pl.edu.agh.racing.cmodel.security.jwt.JwtProvider;

import javax.transaction.Transactional;
import java.time.Duration;
import java.time.Instant;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;

@Service
@AllArgsConstructor
public class AuthService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final VerificationTokenRepository verificationTokenRepository;
    private final MailService mailService;
    private final AuthenticationManager authenticationManager;
    private final JwtProvider jwtProvider;
    private final RoleRepository roleRepository;

    @Transactional
    public void signup(RegisterRequest registerRequest) {
        User user = User.builder()
                .name(registerRequest.getName())
                .surname(registerRequest.getSurname())
                .email(registerRequest.getEmail())
                .password(passwordEncoder.encode(registerRequest.getPassword()))
                .roles(getInitialSetOfRoles())
                .build();

        userRepository.save(user);

        String token = generateVerificationToken(user);
        mailService.sendMail(new NotificationEmailDto("Please Activate Your Account",
                user.getEmail(), "http://localhost:8081/api/auth/accountVerification/" + token));
    }

    private Set<Role> getInitialSetOfRoles() {
        return Set.of(roleRepository.findRoleByRole(ERole.ROLE_NEWBIE)
                .orElse(roleRepository.save(new Role(null, ERole.ROLE_NEWBIE, new HashSet<>()))));
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

    public AuthenticationResponse login(LoginRequest loginRequest) {
        Authentication authenticate = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(),
                loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authenticate);
        String token = jwtProvider.generateToken(authenticate);
        return new AuthenticationResponse(token, loginRequest.getEmail());
    }
}
