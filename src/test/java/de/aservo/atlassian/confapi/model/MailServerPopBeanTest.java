package de.aservo.atlassian.confapi.model;

import com.atlassian.mail.server.DefaultTestPopMailServerImpl;
import com.atlassian.mail.server.PopMailServer;
import de.aservo.atlassian.confapi.junit.AbstractBeanTest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.atlassian.mail.MailConstants.DEFAULT_TIMEOUT;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class MailServerPopBeanTest extends AbstractBeanTest {

    @Test
    public void testDefaultConstructor() {
        final MailServerPopBean bean = new MailServerPopBean();

        assertNull(bean.getName());
        assertNull(bean.getDescription());
        assertNull(bean.getProtocol());
        assertNull(bean.getHost());
        assertNull(bean.getPort());
        assertEquals(DEFAULT_TIMEOUT, bean.getTimeout());
        assertNull(bean.getUsername());
        assertNull(bean.getPassword());
    }

    @Test
    public void testFromConstructor() throws Exception {
        final PopMailServer server = new DefaultTestPopMailServerImpl();
        final MailServerPopBean bean = MailServerPopBean.from(server);

        assertEquals(server.getName(), bean.getName());
        assertEquals(server.getDescription(), bean.getDescription());
        assertEquals(server.getMailProtocol().getProtocol(), bean.getProtocol());
        assertEquals(server.getHostname(), bean.getHost());
        assertEquals(Integer.valueOf(server.getPort()), bean.getPort());
        assertEquals(server.getTimeout(), bean.getTimeout());
        assertEquals(server.getUsername(), bean.getUsername());
        assertNull(bean.getPassword());
    }

    @Test
    public void testFromConstructorHideEmptyDescription() throws Exception {
        final PopMailServer server = new DefaultTestPopMailServerImpl();
        server.setDescription("");
        final MailServerPopBean bean = MailServerPopBean.from(server);

        assertNull(bean.getDescription());
    }

}
