package de.aservo.atlassian.confapi.model;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class ErrorCollectionTest {

    public static final String FIRST_ERROR_MESSAGE = "First error message";
    public static final String SECOND_ERROR_MESSAGE = "Second error message";

    private ErrorCollection errorCollection;

    @Before
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
        assertThat(errorCollection.getErrorMessages().size(), is(1));
        assertThat(errorCollection.getErrorMessages(), hasItems(FIRST_ERROR_MESSAGE));
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
        assertThat(errorCollection.getErrorMessages().size(), is(2));
        assertThat(errorCollection.getErrorMessages(), containsInAnyOrder(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE));
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
        assertThat(errorCollection.getErrorMessages().size(), is(2));
        assertThat(errorCollection.getErrorMessages(), containsInAnyOrder(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE));
    }

    @Test
    public void testOfErrorMessageList() {
        errorCollection = ErrorCollection.of(Arrays.asList(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE));
        assertTrue(errorCollection.hasAnyErrors());
        assertThat(errorCollection.getErrorMessages().size(), is(2));
        assertThat(errorCollection.getErrorMessages(), containsInAnyOrder(FIRST_ERROR_MESSAGE, SECOND_ERROR_MESSAGE));
    }

}
