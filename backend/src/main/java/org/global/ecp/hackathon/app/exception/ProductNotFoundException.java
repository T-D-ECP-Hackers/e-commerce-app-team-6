package org.global.ecp.hackathon.app.exception;

public class ProductNotFoundException extends RuntimeException {

    public ProductNotFoundException(final String message) {

        super(message);
    }
}
