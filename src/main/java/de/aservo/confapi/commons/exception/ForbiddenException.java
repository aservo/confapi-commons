package de.aservo.confapi.commons.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.FORBIDDEN;

public class ForbiddenException extends WebApplicationException {

    private static final Response.Status STATUS = FORBIDDEN;

    public ForbiddenException(String message) {
        super(new Exception(message), STATUS);
    }

    public ForbiddenException(Throwable cause) {
        super(cause, STATUS);
    }

}
