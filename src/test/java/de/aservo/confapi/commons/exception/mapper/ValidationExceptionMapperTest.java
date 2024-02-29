package de.aservo.confapi.commons.exception.mapper;

import de.aservo.confapi.commons.model.ErrorCollection;
import jakarta.validation.ValidationException;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static jakarta.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ValidationExceptionMapperTest {

    private static final String MESSAGES = "ValidationError1\nValidationError2";

    @Test
    public void testResponse() {
        final ValidationExceptionMapper validationExceptionMapper = new ValidationExceptionMapper();
        final ValidationException validationException = new ValidationException(MESSAGES);
        final Response response = validationExceptionMapper.toResponse(validationException);
        final ErrorCollection errorCollection = (ErrorCollection) response.getEntity();

        assertEquals(BAD_REQUEST.getStatusCode(), response.getStatus(), "Validation exceptions should be mapped to bad request exceptions");
        assertEquals(MESSAGES.split("\n").length, errorCollection.getErrorMessages().size(), "The response error collection size is wrong");
    }

}
