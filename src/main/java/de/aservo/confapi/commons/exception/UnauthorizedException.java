package de.aservo.confapi.commons.exception;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;

public class UnauthorizedException extends WebApplicationException {

    private static final Response.Status STATUS = UNAUTHORIZED;

    public UnauthorizedException(String message) {
        super(new Exception(message), STATUS);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause, STATUS);
    }

}
