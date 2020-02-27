package de.aservo.atlassian.confapi.exception;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NoContentExceptionTest {

    public static final String MESSAGE = "No content";

    @Test
    public void test() {
        final NoContentException noContentException = new NoContentException(MESSAGE);
        assertEquals(MESSAGE, noContentException.getMessage());
    }

}
