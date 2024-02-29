package de.aservo.confapi.commons.junit;

import org.junit.jupiter.api.Test;

import jakarta.ws.rs.WebApplicationException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractExceptionTest extends AbstractTest {

    private static final String CLASS_SUFFIX = "Exception";

    private static final String MESSAGE = "Exception";

    @Test
    public void exceptionClassNameShouldEndWithSuffixException() {
        final String beanClassName = getBaseClass().getSimpleName();
        assertTrue(beanClassName.endsWith(CLASS_SUFFIX), "The model class name should end with suffix " + CLASS_SUFFIX);
    }

    @Test
    public void exceptionClassShouldExtendWebApplicationException() {
        assertTrue(WebApplicationException.class.isAssignableFrom(getBaseClass()), "The exception class should extend WebApplicationException");
    }

    @Test
    public void exceptionConstructorsShouldBehaveEqually() throws Exception {
        final Class<?> baseClass = getBaseClass();
        final WebApplicationException messageException = (WebApplicationException) baseClass.getConstructor(String.class)
                .newInstance(MESSAGE);
        final WebApplicationException throwableException = (WebApplicationException) baseClass.getConstructor(Throwable.class)
                .newInstance(new Exception(MESSAGE));

        assertEquals(throwableException.getMessage(), messageException.getMessage(), "The message for both constructors should be equal");
        assertEquals(throwableException.getResponse().getStatus(), messageException.getResponse().getStatus(), "The status code for both constructors should be equal");
    }

}
