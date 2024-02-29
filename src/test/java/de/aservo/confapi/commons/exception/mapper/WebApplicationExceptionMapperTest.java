package de.aservo.confapi.commons.exception.mapper;

import de.aservo.confapi.commons.exception.NotFoundException;
import de.aservo.confapi.commons.model.ErrorCollection;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WebApplicationExceptionMapperTest {

    private static final String MESSAGE = "Space with key 'KEY' does not exist";

    @Test
    public void testResponse() {
        final WebApplicationExceptionMapper webApplicationExceptionMapper = new WebApplicationExceptionMapper();
        final NotFoundException notFoundException = new NotFoundException(MESSAGE);
        final Response response = webApplicationExceptionMapper.toResponse(notFoundException);
        final ErrorCollection errorCollection = (ErrorCollection) response.getEntity();
        final String errorMessage = errorCollection.getErrorMessages().iterator().next();

        assertEquals(notFoundException.getResponse().getStatus(), response.getStatus(), "Web application exceptions should be mapped to their own response status");
        assertEquals(1, errorCollection.getErrorMessages().size(), "The response error collection size is wrong");
        assertEquals(MESSAGE, errorMessage);
    }

}
