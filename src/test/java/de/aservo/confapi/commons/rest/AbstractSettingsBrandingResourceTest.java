package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.SettingsBrandingColorSchemeBean;
import de.aservo.confapi.commons.service.api.SettingsBrandingService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.ws.rs.core.Response;
import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AbstractSettingsBrandingResourceTest {

    @Mock
    private SettingsBrandingService brandingService;

    private TestSettingsBrandingResourceImpl resource;

    @Before
    public void setup() {
        resource = new TestSettingsBrandingResourceImpl(brandingService);
    }

    @Test
    public void testGetColourScheme() {
        final SettingsBrandingColorSchemeBean bean = SettingsBrandingColorSchemeBean.EXAMPLE_1;

        doReturn(bean).when(brandingService).getColourScheme();

        final Response response = resource.getBrandingColorScheme();
        assertEquals(200, response.getStatus());
        final SettingsBrandingColorSchemeBean colourSchemeBean = (SettingsBrandingColorSchemeBean) response.getEntity();

        assertEquals(colourSchemeBean, bean);
    }

    @Test
    public void testSetColourScheme() {
        final SettingsBrandingColorSchemeBean bean = SettingsBrandingColorSchemeBean.EXAMPLE_1;

        doReturn(bean).when(brandingService).setColourScheme(bean);

        final Response response = resource.setBrandingColorScheme(bean);
        assertEquals(200, response.getStatus());
        final SettingsBrandingColorSchemeBean colourSchemeBean = (SettingsBrandingColorSchemeBean) response.getEntity();

        assertEquals(colourSchemeBean, bean);
    }

    @Test
    public void testGetLogo() {
        final InputStream stream = createDummyInputStream();

        doReturn(stream).when(brandingService).getLogo();

        final Response response = resource.getBrandingLogo();
        assertEquals(200, response.getStatus());
        final InputStream inputStream = (InputStream) response.getEntity();

        assertNotNull(inputStream);
    }

    @Test
    public void testSetLogo() {
        final InputStream stream = createDummyInputStream();

        final Response response = resource.setBrandingLogo(stream);

        assertEquals(200, response.getStatus());
    }

    @Test
    public void testGetFavicon() {
        final InputStream stream = createDummyInputStream();

        doReturn(stream).when(brandingService).getFavicon();

        final Response response = resource.getBrandingFavicon();
        assertEquals(200, response.getStatus());
        final InputStream inputStream = (InputStream) response.getEntity();

        assertNotNull(inputStream);
    }

    @Test
    public void testSetFavicon() {
        final InputStream stream = createDummyInputStream();

        final Response response = resource.setBrandingFavicon(stream);

        assertEquals(200, response.getStatus());
    }

    private InputStream createDummyInputStream() {
        return new ByteArrayInputStream("TEST-STREAM".getBytes());
    }
}
