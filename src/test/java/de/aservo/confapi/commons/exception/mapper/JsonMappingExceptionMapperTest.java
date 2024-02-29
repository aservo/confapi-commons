package de.aservo.confapi.commons.exception.mapper;

import com.fasterxml.jackson.databind.JsonMappingException;
import de.aservo.confapi.commons.model.ErrorCollection;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonMappingExceptionMapperTest {

    private static final String MESSAGE = "Message";

    @Test
    public void testResponse() {
        final JsonMappingExceptionMapper jsonMappingExceptionMapper = new JsonMappingExceptionMapper();
        final JsonMappingException jsonMappingException = new JsonMappingException(MESSAGE);
        final Response response = jsonMappingExceptionMapper.toResponse(jsonMappingException);
        final ErrorCollection errorCollection = (ErrorCollection) response.getEntity();

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus(), "Json mapping exceptions should be mapped to bad request exceptions");
        assertEquals(1, errorCollection.getErrorMessages().size(), "The response error collection size is wrong");
    }

}
