package org.global.ecp.hackathon.app.authentication;

import org.global.ecp.hackathon.app.user.UserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class AuthController {

    private final AuthService authService;
    private final AuthRegistrationService authRegistrationService;

    public AuthController(final AuthService authService,
                          final AuthRegistrationService authRegistrationService) {

        this.authService = authService;
        this.authRegistrationService = authRegistrationService;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<Boolean> authenticateUser(@RequestBody final AuthenticationRequest authenticationRequest) {

        return ResponseEntity.ok(authService.authenticate(authenticationRequest));
    }

    @PostMapping("/register")
    public ResponseEntity<Boolean> authenticateUser(@RequestBody final UserDto userDto) {

        return ResponseEntity.ok(authRegistrationService.register(userDto));
    }


}
