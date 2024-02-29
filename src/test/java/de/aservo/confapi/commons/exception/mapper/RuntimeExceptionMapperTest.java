package de.aservo.confapi.commons.exception.mapper;

import de.aservo.confapi.commons.model.ErrorCollection;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static jakarta.ws.rs.core.Response.Status.INTERNAL_SERVER_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RuntimeExceptionMapperTest {

    private static final String MESSAGE = "Message";

    @Test
    public void testResponse() {
        final RuntimeExceptionMapper runtimeExceptionMapper = new RuntimeExceptionMapper();
        final RuntimeException runtimeException = new NullPointerException(MESSAGE);
        final Response response = runtimeExceptionMapper.toResponse(runtimeException);
        final ErrorCollection errorCollection = (ErrorCollection) response.getEntity();

        assertEquals(INTERNAL_SERVER_ERROR.getStatusCode(), response.getStatus(), "Runtime exceptions should be mapped to internal server error");
        assertEquals(1, errorCollection.getErrorMessages().size(), "The response error collection size is wrong");
    }

}
