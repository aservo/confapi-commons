package de.aservo.confapi.commons.exception.mapper;

import com.fasterxml.jackson.databind.JsonMappingException;
import de.aservo.confapi.commons.model.ErrorCollection;
import org.junit.Test;

import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class JsonMappingExceptionMapperTest {

    private static final String MESSAGE = "Message";

    @Test
    public void testResponse() {
        final JsonMappingExceptionMapper jsonMappingExceptionMapper = new JsonMappingExceptionMapper();
        final JsonMappingException jsonMappingException = new JsonMappingException(MESSAGE);
        final Response response = jsonMappingExceptionMapper.toResponse(jsonMappingException);

        assertEquals("Json mapping exceptions should be mapped to bad request exceptions",
                BAD_REQUEST.getStatusCode(), response.getStatus());

        final ErrorCollection errorCollection = (ErrorCollection) response.getEntity();

        assertEquals("The response error collection size is wrong",
                1, errorCollection.getErrorMessages().size());
    }

}
