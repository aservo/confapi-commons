package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.LicenseBean;
import de.aservo.confapi.commons.model.LicensesBean;
import de.aservo.confapi.commons.service.api.LicensesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AbstractLicensesResourceTest {

    @Mock
    private LicensesService licensesService;

    private TestLicensesResourceImpl resource;

    @Before
    public void setup() {
        resource = new TestLicensesResourceImpl(licensesService);
    }

    @Test
    public void testGetLicenses() {
        final LicensesBean bean = LicensesBean.EXAMPLE_1;

        doReturn(bean).when(licensesService).getLicenses();

        final Response response = resource.getLicenses();
        assertEquals(200, response.getStatus());
        final LicensesBean licensesBean = (LicensesBean) response.getEntity();

        assertEquals(licensesBean, bean);
    }

    @Test
    public void testAddLicense() {
        final LicenseBean bean = LicenseBean.EXAMPLE_1;

        doReturn(bean).when(licensesService).addLicense(bean);

        final Response response = resource.addLicense(bean);
        assertEquals(200, response.getStatus());
        final LicenseBean responseBean = (LicenseBean) response.getEntity();

        assertEquals(bean, responseBean);
    }
}
