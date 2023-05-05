package org.global.ecp.hackathon.app.authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.global.ecp.hackathon.app.TestConstants.EMAIL;
import static org.global.ecp.hackathon.app.TestConstants.USERNAME;
import static org.global.ecp.hackathon.app.TestUtils.getUserDto;
import static org.mockito.Mockito.when;

import org.global.ecp.hackathon.app.exception.UserAlreadyExistsException;
import org.global.ecp.hackathon.app.user.UserDto;
import org.global.ecp.hackathon.app.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthRegistrationServiceTest {

    private static final UserDto USER_DTO = getUserDto();

    @Mock private UserRepository userRepository;
    private AuthRegistrationService underTest;

    @BeforeEach
    void setUp() {

        underTest = new AuthRegistrationService(userRepository);
    }

    @Test
    void registerWillReturnTrueIfNewUserDoesNotExistAndCanBeCreated() {

        when(userRepository.existsUserByUsername(USERNAME)).thenReturn(false);
        when(userRepository.existsUserByEmail(EMAIL)).thenReturn(false);
        assertThat(underTest.register(USER_DTO)).isTrue();
    }

    @Test
    void registerWillThrowUserAlreadyExistsExceptionIfNewUserUsernameAlreadyExistsInDB() {

        when(userRepository.existsUserByUsername(USERNAME)).thenReturn(true);
        assertThatThrownBy(() -> underTest.register(USER_DTO))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessage("Following username '" + USER_DTO.getUsername() + "' or email: '" + USER_DTO.getEmail() + "' already exists");

    }

    @Test
    void registerWillThrowUserAlreadyExistsExceptionIfNewUserEmailAlreadyExistsInDB() {

        when(userRepository.existsUserByEmail(EMAIL)).thenReturn(true);
        assertThatThrownBy(() -> underTest.register(USER_DTO))
                .isInstanceOf(UserAlreadyExistsException.class)
                .hasMessage("Following username '" + USER_DTO.getUsername() + "' or email: '" + USER_DTO.getEmail() + "' already exists");

    }
}