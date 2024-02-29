package de.aservo.confapi.commons.exception.mapper;

import de.aservo.confapi.commons.model.ErrorCollection;

import jakarta.validation.ValidationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import java.util.Arrays;

@Provider
public class ValidationExceptionMapper implements ExceptionMapper<ValidationException> {

    public Response toResponse(ValidationException e) {
        final ErrorCollection errorCollection = new ErrorCollection();
        errorCollection.addErrorMessages(Arrays.asList(e.getMessage().split("\n")));
        return Response.status(Response.Status.BAD_REQUEST).entity(errorCollection).build();
    }

}
