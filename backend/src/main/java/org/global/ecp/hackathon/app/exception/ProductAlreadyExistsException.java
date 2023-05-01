package org.global.ecp.hackathon.app.exception;

public class ProductAlreadyExistsException extends RuntimeException {

    public ProductAlreadyExistsException(final String message) {

        super(message);
    }
}
