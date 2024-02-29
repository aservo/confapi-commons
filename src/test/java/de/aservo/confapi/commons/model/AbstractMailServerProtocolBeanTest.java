package de.aservo.confapi.commons.model;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(MockitoExtension.class)
public class AbstractMailServerProtocolBeanTest {

    @Test
    public void testTimeoutHasDefaultValue() {
        final long timeout = 12345L;

        final AbstractMailServerProtocolBean beanWithTimeoutSet = new AbstractMailServerProtocolBean() {};
        beanWithTimeoutSet.setTimeout(timeout);
        assertEquals(timeout, beanWithTimeoutSet.getTimeout().longValue());

        final AbstractMailServerProtocolBean beanWithoutTimeoutSet = new AbstractMailServerProtocolBean() {};
        assertNotNull(beanWithoutTimeoutSet.getTimeout());
    }

    @Test
    public void testSetPortUsingIntAndString() {
        final int port = 1337;

        final AbstractMailServerProtocolBean beanUsingInt = new AbstractMailServerProtocolBean() {};
        beanUsingInt.setPort(port);

        final AbstractMailServerProtocolBean beanUsingString = new AbstractMailServerProtocolBean() {};
        beanUsingString.setPort(String.valueOf(port));

        assertEquals(beanUsingInt.getPort(), beanUsingString.getPort());
    }

}
