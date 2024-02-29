package de.aservo.confapi.commons.exception.mapper;

import de.aservo.confapi.commons.exception.NotFoundException;
import de.aservo.confapi.commons.model.ErrorCollection;
import org.junit.Test;

import jakarta.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class WebApplicationExceptionMapperTest {

    private static final String MESSAGE = "Space with key 'KEY' does not exist";

    @Test
    public void testResponse() {
        final WebApplicationExceptionMapper webApplicationExceptionMapper = new WebApplicationExceptionMapper();
        final NotFoundException notFoundException = new NotFoundException(MESSAGE);
        final Response response = webApplicationExceptionMapper.toResponse(notFoundException);

        assertEquals("Web application exceptions should be mapped to their own response status",
                notFoundException.getResponse().getStatus(), response.getStatus());

        final ErrorCollection errorCollection = (ErrorCollection) response.getEntity();

        assertEquals("The response error collection size is wrong",
                1, errorCollection.getErrorMessages().size());

        final String errorMessage = errorCollection.getErrorMessages().iterator().next();

        assertEquals(MESSAGE, errorMessage);
    }

}
