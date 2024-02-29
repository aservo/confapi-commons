package de.aservo.confapi.commons.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;

public class BadRequestException extends WebApplicationException {

    private static final Response.Status STATUS = BAD_REQUEST;

    public BadRequestException(String message) {
        super(new Exception(message), STATUS);
    }

    public BadRequestException(Throwable cause) {
        super(cause, STATUS);
    }

}
