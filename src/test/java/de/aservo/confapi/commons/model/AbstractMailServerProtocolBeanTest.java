package de.aservo.confapi.commons.model;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(MockitoJUnitRunner.class)
public class AbstractMailServerProtocolBeanTest {

    @Test
    public void testTimeoutHasDefaultValue() {
        final long timeout = 12345L;

        final AbstractMailServerProtocolBean beanWithTimeoutSet = new AbstractMailServerProtocolBean() {};
        beanWithTimeoutSet.setTimeout(timeout);
        assertEquals(timeout, beanWithTimeoutSet.getTimeout().longValue());
    }

    @Test
    public void testTimeoutHasNoDefaultValue() {
        final AbstractMailServerProtocolBean beanWithoutTimeoutSet = new AbstractMailServerProtocolBean() {};
        assertEquals(AbstractMailServerProtocolBean.DEFAULT_TIMEOUT, beanWithoutTimeoutSet.getTimeout());
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
