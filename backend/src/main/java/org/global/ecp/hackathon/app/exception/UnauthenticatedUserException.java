package org.global.ecp.hackathon.app.exception;

public class UnauthenticatedUserException extends RuntimeException {

    public UnauthenticatedUserException(final String message) {

        super(message);
    }
}
