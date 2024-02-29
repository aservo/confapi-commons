package de.aservo.confapi.commons.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ErrorCollectionTest {

    public static final String FIRST_ERROR_MESSAGE = "First error message";
    public static final String SECOND_ERROR_MESSAGE = "Second error message";

    private ErrorCollection errorCollection;

    @BeforeEach
    public void setup() {
        errorCollection = new ErrorCollection();
    }

    @Test
    public void testDefaultConstructor() {
        assertFalse(errorCollection.hasAnyErrors());
    }

    @Test
    public void testAddErrorMessage() {
        errorCollection.addErrorMessage(FIRST_ERROR_MESSAGE);
        assertTrue(errorCollection.hasAnyErrors());
        assertEquals(1, errorCollection.getErrorMessages().size());
        assertTrue(errorCollection.getErrorMessages().contains(FIRST_ERROR_MESSAGE));
    }

    @Test
    public void testAddNullErrorMessage() {
        errorCollection.addErrorMessage(null);
        assertFalse(errorCollection.hasAnyErrors());
    }

    @Test
    public void testAddErrorMessageList() {
        errorCollection.addErrorMessages(Arrays.asList(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE));
        assertTrue(errorCollection.hasAnyErrors());
        assertEquals(2, errorCollection.getErrorMessages().size());
        assertTrue(errorCollection.getErrorMessages().containsAll(Arrays.asList(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE)));
    }

    @Test
    public void testAddNullErrorMessageList() {
        errorCollection.addErrorMessages(null);
        assertFalse(errorCollection.hasAnyErrors());
    }

    @Test
    public void testOfErrorMessages() {
        errorCollection = ErrorCollection.of(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE);
        assertTrue(errorCollection.hasAnyErrors());
        assertEquals(2, errorCollection.getErrorMessages().size());
        assertTrue(errorCollection.getErrorMessages().containsAll(Arrays.asList(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE)));
    }

    @Test
    public void testOfErrorMessageList() {
        errorCollection = ErrorCollection.of(Arrays.asList(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE));
        assertTrue(errorCollection.hasAnyErrors());
        assertEquals(2, errorCollection.getErrorMessages().size());
        assertTrue(errorCollection.getErrorMessages().containsAll(Arrays.asList(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE)));
    }
}
