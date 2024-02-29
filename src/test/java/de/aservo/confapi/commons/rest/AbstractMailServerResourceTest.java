package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.MailServerPopBean;
import de.aservo.confapi.commons.model.MailServerSmtpBean;
import de.aservo.confapi.commons.service.api.MailServerService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AbstractMailServerResourceTest {

    @Mock
    private MailServerService mailServerService;

    private TestMailServerResourceImpl resource;

    @Before
    public void setup() {
        resource = new TestMailServerResourceImpl(mailServerService);
    }

    @Test
    public void testGetMailServerSmtpSettings() {
        final MailServerSmtpBean bean = MailServerSmtpBean.EXAMPLE_1;

        doReturn(bean).when(mailServerService).getMailServerSmtp();

        final Response response = resource.getMailServerSmtp();
        assertEquals(200, response.getStatus());
        final MailServerSmtpBean smtpBean = (MailServerSmtpBean) response.getEntity();

        assertEquals(smtpBean, bean);
    }

    @Test
    public void testSetMailServerSmtpSettings() {
        final MailServerSmtpBean bean = MailServerSmtpBean.EXAMPLE_1;

        doReturn(bean).when(mailServerService).setMailServerSmtp(bean);

        final Response response = resource.setMailServerSmtp(bean);
        assertEquals(200, response.getStatus());
        final MailServerSmtpBean smtpBean = (MailServerSmtpBean) response.getEntity();

        assertEquals(smtpBean, bean);
    }

    @Test
    public void testGetMailServerPopSettings() {
        final MailServerPopBean bean = MailServerPopBean.EXAMPLE_1;

        doReturn(bean).when(mailServerService).getMailServerPop();

        final Response response = resource.getMailServerPop();
        assertEquals(200, response.getStatus());
        final MailServerPopBean popBean = (MailServerPopBean) response.getEntity();

        assertEquals(popBean, bean);
    }

    @Test
    public void testSetMailServerPopSettings() {
        final MailServerPopBean bean = MailServerPopBean.EXAMPLE_1;

        doReturn(bean).when(mailServerService).setMailServerPop(bean);

        final Response response = resource.setMailServerPop(bean);
        assertEquals(200, response.getStatus());
        final MailServerPopBean popBean = (MailServerPopBean) response.getEntity();

        assertEquals(popBean, bean);
    }
}
