package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.SettingsBean;
import de.aservo.confapi.commons.service.api.SettingsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AbstractSettingsResourceTest {

    @Mock
    private SettingsService settingsService;

    private TestSettingsResourceImpl resource;

    @Before
    public void setup() {
        resource = new TestSettingsResourceImpl(settingsService);
    }

    @Test
    public void testGetSettings() {
        final SettingsBean bean = SettingsBean.EXAMPLE_1;

        doReturn(bean).when(settingsService).getSettings();

        final Response response = resource.getSettings();
        assertEquals(200, response.getStatus());
        final SettingsBean settingsBean = (SettingsBean) response.getEntity();

        assertEquals(settingsBean, bean);
    }

    @Test
    public void testSetSettings() {
        final SettingsBean bean = SettingsBean.EXAMPLE_1;

        doReturn(bean).when(settingsService).setSettings(bean);

        final Response response = resource.setSettings(bean);
        assertEquals(200, response.getStatus());
        final SettingsBean settingsBean = (SettingsBean) response.getEntity();

        assertEquals(settingsBean, bean);
    }
}
