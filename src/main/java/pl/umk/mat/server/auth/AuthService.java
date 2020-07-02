package pl.umk.mat.server.auth;

import net.bytebuddy.utility.RandomString;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.umk.mat.server.auth.utils.UserPrincipal;
import pl.umk.mat.server.users.User;
import pl.umk.mat.server.users.UserRepository;
import pl.umk.mat.server.utils.exceptions.ResourceAlreadyExist;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Service
public class AuthService {
    private AuthenticationManager authenticationManager;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;
    private ApiLoginEntriesRepository apiLoginEntriesRepository;

    public AuthService(
            AuthenticationManager authenticationManager,
            UserRepository userRepository,
            PasswordEncoder passwordEncoder,
            ApiLoginEntriesRepository apiLoginEntriesRepository
    ) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.apiLoginEntriesRepository = apiLoginEntriesRepository;
    }


    public AuthResponse login(LoginRequest loginRequest) {
        UserPrincipal user = (UserPrincipal) authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(
                        loginRequest.getEmail(),
                        loginRequest.getPassword()
                )).getPrincipal();
        String token = RandomString.make(50);
        apiLoginEntriesRepository.save(new ApiLoginEntries(
                user.getUser(),
                token,
                Instant.now().plus(1, ChronoUnit.HOURS))
        );
        return new AuthResponse(user.getUser(), token);
    }

    @Transactional
    public AuthResponse register(RegisterRequest registerRequest) {
        if (userRepository.existsUserByEmail(registerRequest.getEmail())) {
            throw new ResourceAlreadyExist();
        }
        User user = userRepository.save(new User(
                registerRequest.getEmail(),
                registerRequest.getFirstName(),
                registerRequest.getLastName(),
                passwordEncoder.encode(registerRequest.getPassword())
        ));
        String token = RandomString.make(50);
        apiLoginEntriesRepository.save(new ApiLoginEntries(
                user,
                token,
                Instant.now().plus(1, ChronoUnit.HOURS))
        );
        return new AuthResponse(user, token);
    }
}
