package de.aservo.confapi.commons.exception.mapper;

import com.fasterxml.jackson.databind.JsonMappingException;
import de.aservo.confapi.commons.model.ErrorCollection;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class JsonMappingExceptionMapper implements ExceptionMapper<JsonMappingException> {

    public Response toResponse(JsonMappingException e) {
        final ErrorCollection errorCollection = new ErrorCollection();
        errorCollection.addErrorMessage(e.getMessage());
        return Response.status(Response.Status.BAD_REQUEST).entity(errorCollection).build();
    }

}
