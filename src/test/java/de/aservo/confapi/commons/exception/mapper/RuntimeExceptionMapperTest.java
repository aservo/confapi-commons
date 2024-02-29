package de.aservo.confapi.commons.exception.mapper;

import de.aservo.confapi.commons.model.ErrorCollection;
import org.junit.Test;

import jakarta.ws.rs.core.Response;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static org.junit.Assert.assertEquals;

public class RuntimeExceptionMapperTest {

    private static final String MESSAGE = "Message";

    @Test
    public void testResponse() {
        final RuntimeExceptionMapper runtimeExceptionMapper = new RuntimeExceptionMapper();
        final RuntimeException runtimeException = new NullPointerException(MESSAGE);
        final Response response = runtimeExceptionMapper.toResponse(runtimeException);

        assertEquals("Runtime exceptions should be mapped to internal server error",
                INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus());

        final ErrorCollection errorCollection = (ErrorCollection) response.getEntity();

        assertEquals("The response error collection size is wrong",
                1, errorCollection.getErrorMessages().size());
    }

}
