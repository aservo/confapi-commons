package de.aservo.confapi.commons.exception;

import de.aservo.confapi.commons.junit.AbstractExceptionTest;
import org.junit.Test;

import javax.ws.rs.core.Response;

import static de.aservo.confapi.commons.exception.ServiceUnavailableException.HEADER_RETRY_AFTER;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;

public class ServiceUnavailableExceptionTest extends AbstractExceptionTest {

    @Test
    public void testCreateResponseWithRetryAfterHeader() {
        final int retryAfterInSeconds = 60;
        final Response response = ServiceUnavailableException.response(retryAfterInSeconds);
        assertEquals(String.valueOf(retryAfterInSeconds), response.getMetadata().getFirst(HEADER_RETRY_AFTER));
    }

    @Test
    public void testCreateResponseWithoutRetryAfterHeader() {
        final Response response = ServiceUnavailableException.response(null);
        assertFalse(response.getMetadata().containsKey(HEADER_RETRY_AFTER));
    }

}
