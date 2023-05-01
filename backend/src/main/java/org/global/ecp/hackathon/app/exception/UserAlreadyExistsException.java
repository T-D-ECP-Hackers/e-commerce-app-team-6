package org.global.ecp.hackathon.app.exception;

public class UserAlreadyExistsException extends RuntimeException {

    public UserAlreadyExistsException(final String message) {

        super(message);
    }
}
