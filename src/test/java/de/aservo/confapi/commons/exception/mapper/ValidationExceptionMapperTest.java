package de.aservo.confapi.commons.exception.mapper;

import de.aservo.confapi.commons.model.ErrorCollection;
import org.junit.Test;

import jakarta.validation.ValidationException;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status.BAD_REQUEST;
import static org.junit.Assert.assertEquals;

public class ValidationExceptionMapperTest {

    private static final String MESSAGES = "ValidationError1\nValidationError2";

    @Test
    public void testResponse() {
        final ValidationExceptionMapper validationExceptionMapper = new ValidationExceptionMapper();
        final ValidationException validationException = new ValidationException(MESSAGES);
        final Response response = validationExceptionMapper.toResponse(validationException);

        assertEquals("Validation exceptions should be mapped to bad request exceptions",
                BAD_REQUEST.getStatusCode(), response.getStatus());

        final ErrorCollection errorCollection = (ErrorCollection) response.getEntity();

        assertEquals("The response error collection size is wrong",
                MESSAGES.split("\n").length, errorCollection.getErrorMessages().size());
    }

}
