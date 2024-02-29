package de.aservo.confapi.commons.junit;

import org.junit.Test;

import jakarta.ws.rs.WebApplicationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public abstract class AbstractExceptionTest extends AbstractTest {

    private static final String CLASS_SUFFIX = "Exception";

    private static final String MESSAGE = "Exception";

    @Test
    public void exceptionClassNameShouldEndWithSuffixException() {
        final String beanClassName = getBaseClass().getSimpleName();
        assertTrue("The model class name should end with suffix " + CLASS_SUFFIX,
                beanClassName.endsWith(CLASS_SUFFIX));
    }

    @Test
    public void exceptionClassShouldExtendWebApplicationException() {
        assertTrue("The exception class should extend WebApplicationException",
                WebApplicationException.class.isAssignableFrom(getBaseClass()));
    }

    @Test
    public void exceptionConstructorsShouldBehaveEqually() throws Exception {
        final Class<?> baseClass = getBaseClass();
        final WebApplicationException messageException = (WebApplicationException) baseClass.getConstructor(String.class)
                .newInstance(MESSAGE);
        final WebApplicationException throwableException = (WebApplicationException) baseClass.getConstructor(Throwable.class)
                .newInstance(new Exception(MESSAGE));

        assertEquals("The message for both constructors should be equal",
                throwableException.getMessage(), messageException.getMessage());
        assertEquals("The status code for both constructors should be equal",
                throwableException.getResponse().getStatus(), messageException.getResponse().getStatus());
    }

}
