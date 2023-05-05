package org.global.ecp.hackathon.app.authentication;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.global.ecp.hackathon.app.TestConstants.INCORRECT_USERNAME;
import static org.global.ecp.hackathon.app.TestConstants.PASSWORD;
import static org.global.ecp.hackathon.app.TestConstants.USER;
import static org.global.ecp.hackathon.app.TestConstants.USERNAME;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.global.ecp.hackathon.app.exception.UnauthenticatedUserException;
import org.global.ecp.hackathon.app.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    private static final String INCORRECT_PASSWORD = "incorrect_password";
    private static final AuthenticationRequest AUTHENTICATION_REQUEST = getAuthenticationRequest(USERNAME, PASSWORD);
    private static final AuthenticationRequest INCORRECT_USERNAME_AUTHENTICATION_REQUEST = getAuthenticationRequest(
            INCORRECT_USERNAME,
            PASSWORD);
    private static final AuthenticationRequest INCORRECT_PASSWORD_AUTHENTICATION_REQUEST = getAuthenticationRequest(
            USERNAME,
            INCORRECT_PASSWORD);

    @Mock private UserRepository userRepository;

    private AuthService underTest;

    @BeforeEach
    void setUp() {

        underTest = new AuthService(userRepository);
    }

    @Test
    void authenticateWillReturnTrueIfAuthenticationRequestIsValid() {

        when(userRepository.getUserByUsername(USERNAME)).thenReturn(Optional.of(USER));
        assertThat(underTest.authenticate(AUTHENTICATION_REQUEST)).isTrue();
    }

    @Test
    void authenticateWillThrowAnUnauthenticatedUserExceptionIfAuthenticationRequestUsernameIsIncorrect() {

        when(userRepository.getUserByUsername(USERNAME)).thenReturn(Optional.of(USER));
        assertThatThrownBy(() -> underTest.authenticate(INCORRECT_PASSWORD_AUTHENTICATION_REQUEST))
                .isInstanceOf(UnauthenticatedUserException.class)
                .hasMessage("Authentication details incorrect for user: '" + USER.getUsername() + "'");
    }

    @Test
    void authenticateWillThrowAnUnauthenticatedUserExceptionIfAuthenticationRequestPasswordIsIncorrect() {

        when(userRepository.getUserByUsername(INCORRECT_USERNAME)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> underTest.authenticate(INCORRECT_USERNAME_AUTHENTICATION_REQUEST))
                .isInstanceOf(UnauthenticatedUserException.class)
                .hasMessage("Username does not exist in db: '" + INCORRECT_USERNAME + "'");
    }

    @Test
    void getUserWillReturnAUserIfThatUserExistsInTheDB() {

        when(userRepository.getUserByUsername(USERNAME)).thenReturn(Optional.of(USER));
        assertThat(underTest.getUser(USERNAME)).isEqualTo(USER);
    }

    @Test
    void getUserWillThrowAnUnauthenticatedUserExceptionIfThatUserDoesNotExistInTheDB() {

        when(userRepository.getUserByUsername(INCORRECT_USERNAME)).thenReturn(Optional.empty());
        assertThatThrownBy(() -> underTest.getUser(INCORRECT_USERNAME))
                .isInstanceOf(UnauthenticatedUserException.class)
                .hasMessage("Username does not exist in db: '" + INCORRECT_USERNAME + "'");
    }

    private static AuthenticationRequest getAuthenticationRequest(final String incorrectUsername,
                                                                  final String password) {

        return AuthenticationRequest.builder()
                                    .username(incorrectUsername)
                                    .password(password)
                                    .build();
    }
}