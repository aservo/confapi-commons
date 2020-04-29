package de.aservo.atlassian.confapi.exception.api;

import de.aservo.atlassian.confapi.model.ErrorCollection;

import javax.ws.rs.core.Response;

public abstract class AbstractWebException extends Exception {

    public AbstractWebException(String message) {
        super(message);
    }

    public AbstractWebException(String message, Throwable cause) {
        super(message, cause);
    }

    public AbstractWebException(Throwable cause) {
        super(cause);
    }

    public abstract Response.Status getStatus();

    public Response getResponse() {
        final ErrorCollection errorCollection = new ErrorCollection();
        errorCollection.addErrorMessage(getMessage());
        return Response.status(getStatus()).entity(errorCollection).build();
    }

}
