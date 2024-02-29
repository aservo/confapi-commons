package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.SettingsBean;
import de.aservo.confapi.commons.service.api.SettingsService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class AbstractSettingsResourceTest {

    @Mock
    private SettingsService settingsService;

    private TestSettingsResourceImpl resource;

    @BeforeEach
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
