package org.global.ecp.hackathon.app.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


@ControllerAdvice
public class ControllerErrorAdvice {

    @ExceptionHandler({UnauthenticatedUserException.class})
    public ResponseEntity<ErrorResponse> handleUnauthorizedExceptions(final Exception e) {

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                             .body(new ErrorResponse(HttpStatus.UNAUTHORIZED, e.getMessage()));
    }

    @ExceptionHandler({UserAlreadyExistsException.class, ProductAlreadyExistsException.class})
    public ResponseEntity<ErrorResponse> handleConflictExceptions(final Exception e) {

        return ResponseEntity.status(HttpStatus.CONFLICT)
                             .body(new ErrorResponse(HttpStatus.CONFLICT, e.getMessage()));
    }

    @ExceptionHandler({BasketNotFoundException.class, ProductNotFoundException.class})
    public ResponseEntity<ErrorResponse> handleNotFoundExceptions(final Exception e) {

        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                             .body(new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()));
    }
}