package de.aservo.atlassian.confapi.model;

import com.atlassian.mail.server.DefaultTestSmtpMailServerImpl;
import com.atlassian.mail.server.SMTPMailServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(MockitoJUnitRunner.class)
public class SettingsBeanTest {

    @Test
    public void testDefaultConstructor() {
        final SettingsBean bean = new SettingsBean();

        assertNull(bean.getBaseurl());
        assertNull(bean.getTitle());
    }

    @Test
    public void testParameterConstructor() throws Exception {
        final String baseurl = "http://localhost/product";
        final String title = "PRODUCT";

        final SMTPMailServer server = new DefaultTestSmtpMailServerImpl();
        final SettingsBean bean = new SettingsBean(baseurl, title);

        assertEquals(baseurl, bean.getBaseurl());
        assertEquals(title, bean.getTitle());
    }

}
