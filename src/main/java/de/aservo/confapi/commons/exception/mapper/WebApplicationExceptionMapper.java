package de.aservo.confapi.commons.exception.mapper;

import de.aservo.confapi.commons.model.ErrorCollection;

import jakarta.ws.rs.WebApplicationException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class WebApplicationExceptionMapper implements ExceptionMapper<WebApplicationException> {

    public Response toResponse(WebApplicationException e) {
        final ErrorCollection errorCollection = new ErrorCollection();

        // there is no way around the cause in the WebApplicationException so that messages always start
        // with the exception name, which will be stripped with the following regex
        errorCollection.addErrorMessage(e.getMessage().replaceFirst("([^:]*: )", ""));

        return Response.fromResponse(e.getResponse()).entity(errorCollection).build();
    }

}
