package de.aservo.atlassian.confapi.model;

import com.atlassian.mail.server.DefaultTestSmtpMailServerImpl;
import com.atlassian.mail.server.SMTPMailServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static com.atlassian.mail.MailConstants.DEFAULT_TIMEOUT;
import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class SmtpMailServerBeanTest {

    @Test
    public void testDefaultConstructor() {
        final SmtpMailServerBean bean = new SmtpMailServerBean();

        assertNull(bean.getName());
        assertNull(bean.getDescription());
        assertNull(bean.getFrom());
        assertNull(bean.getPrefix());
        assertNull(bean.getProtocol());
        assertNull(bean.getHost());
        assertNull(bean.getPort());
        assertFalse(bean.isTls());
        assertEquals(DEFAULT_TIMEOUT, bean.getTimeout());
        assertNull(bean.getUsername());
        assertNull(bean.getPassword());
    }

    @Test
    public void testFromConstructor() throws Exception {
        final SMTPMailServer server = new DefaultTestSmtpMailServerImpl();
        final SmtpMailServerBean bean = SmtpMailServerBean.from(server);

        assertEquals(server.getName(), bean.getName());
        assertEquals(server.getDescription(), bean.getDescription());
        assertEquals(server.getDefaultFrom(), bean.getFrom());
        assertEquals(server.getPrefix(), bean.getPrefix());
        assertEquals(server.getMailProtocol().getProtocol(), bean.getProtocol());
        assertEquals(server.getHostname(), bean.getHost());
        assertEquals(Integer.valueOf(server.getPort()), bean.getPort());
        assertEquals(server.isTlsRequired(), bean.isTls());
        assertEquals(server.getTimeout(), bean.getTimeout());
        assertEquals(server.getUsername(), bean.getUsername());
        assertNull(bean.getPassword());
    }

    @Test
    public void testFromConstructorHideEmptyDescription() throws Exception {
        final SMTPMailServer server = new DefaultTestSmtpMailServerImpl();
        server.setDescription("");
        final SmtpMailServerBean bean = SmtpMailServerBean.from(server);

        assertNull(bean.getDescription());
    }

}
