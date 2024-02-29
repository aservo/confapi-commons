package de.aservo.confapi.commons.exception;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.NOT_FOUND;

public class NotFoundException extends WebApplicationException {

    private static final Response.Status STATUS = NOT_FOUND;

    public NotFoundException(String message) {
        super(message, STATUS);
    }

    public NotFoundException(Throwable cause) {
        super(cause.getMessage(), STATUS);
    }

}
