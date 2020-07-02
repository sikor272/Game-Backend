package pl.umk.mat.server.auth;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/auth")
@Api(description = "This controller provide login/register logic.", tags = "Authentication Controller")
public class AuthController {
    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @ApiOperation("Login with password.")
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.OK)
    public AuthResponse login(
            @RequestBody @Valid LoginRequest loginRequest
    ) {
        return authService.login(loginRequest);
    }

    @ApiOperation("Register with password.")
    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    public AuthResponse register(
            @RequestBody @Valid RegisterRequest registerRequest
    ) {
        return authService.register(registerRequest);
    }
}
