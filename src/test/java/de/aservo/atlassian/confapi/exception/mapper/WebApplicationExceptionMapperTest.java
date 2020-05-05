package de.aservo.atlassian.confapi.exception.mapper;

import de.aservo.atlassian.confapi.exception.NotFoundException;
import de.aservo.atlassian.confapi.model.ErrorCollection;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;

public class WebApplicationExceptionMapperTest {

    private static final String MESSAGE = "Message";

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
    }

}