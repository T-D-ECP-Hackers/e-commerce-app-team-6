package org.global.ecp.hackathon.app.authentication;

import lombok.extern.slf4j.Slf4j;
import org.global.ecp.hackathon.app.exception.UserAlreadyExistsException;
import org.global.ecp.hackathon.app.user.User;
import org.global.ecp.hackathon.app.user.UserDto;
import org.global.ecp.hackathon.app.user.UserRepository;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class AuthRegistrationService {

    private final UserRepository userRepository;

    public AuthRegistrationService(final UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public Boolean register(final UserDto userDto) {

        if (usernameOrEmailAlreadyExists(userDto)) {
            throw new UserAlreadyExistsException("Following username '" + userDto.getUsername() + "' or email: '" + userDto.getEmail() + "' already exists");
        }

        final var user = createUser(userDto);
        userRepository.save(user);
        return true;
    }

    private boolean usernameOrEmailAlreadyExists(final UserDto userDto) {

        return userRepository.existsUserByUsername(userDto.getUsername())
                || userRepository.existsUserByEmail(userDto.getEmail());

    }

    private User createUser(final UserDto userDto) {

        return User.builder()
                   .username(userDto.getUsername())
                   .password(userDto.getPassword())
                   .email(userDto.getEmail())
                   .build();
    }
}
