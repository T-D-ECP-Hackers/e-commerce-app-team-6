package org.global.ecp.hackathon.app.exception;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
public class ErrorResponse {

    public ErrorResponse(final HttpStatus status, final String message) {

        this.status = status.value() + " " + status.getReasonPhrase();
        this.message = message;
    }

    private String status;
    private String message;
}