package org.global.ecp.hackathon.app.authentication;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.exception.UnauthenticatedUserException;
import org.global.ecp.hackathon.app.user.User;
import org.global.ecp.hackathon.app.user.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthService {

    private final UserRepository userRepository;

    public AuthService(final UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Boolean authenticate(final AuthenticationRequest authenticationRequest) {

        final var username = authenticationRequest.getUsername();
        final var user = getUser(username);
        if (!isUserValid(user, authenticationRequest)) {
            throw new UnauthenticatedUserException("Authentication details incorrect for user: '" + username + "'");
        }
        log.info("User authenticated: '{}'", user.getUsername());
        return true;
    }

    public User getUser(final String username) {

        final var optionalUser = userRepository.getUserByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UnauthenticatedUserException("Username does not exist in db: '" + username + "'");
        }
        return optionalUser.get();
    }

    private boolean isUserValid(final User user,
                                final AuthenticationRequest authenticationRequest) {

        return user.getPassword().equals(authenticationRequest.getPassword());
    }
}
