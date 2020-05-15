package de.aservo.atlassian.confapi.rest;

import de.aservo.atlassian.confapi.model.LicenseBean;
import de.aservo.atlassian.confapi.model.LicensesBean;
import de.aservo.atlassian.confapi.service.api.LicensesService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.core.Response;

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
    public void testSetLicenses() {
        final LicensesBean bean = LicensesBean.EXAMPLE_1;

        doReturn(bean).when(licensesService).setLicenses(true, bean);

        final Response response = resource.setLicenses(true, bean);
        assertEquals(200, response.getStatus());
        final LicensesBean licensesBean = (LicensesBean) response.getEntity();

        assertEquals(licensesBean, bean);
    }

    @Test
    public void testSetLicense() {
        final LicensesBean beanToReturn = LicensesBean.EXAMPLE_1;
        final LicenseBean beanArg = beanToReturn.getLicenses().iterator().next();

        doReturn(beanToReturn).when(licensesService).setLicense(true, beanArg);

        final Response response = resource.setLicense(true, beanArg);
        assertEquals(200, response.getStatus());
        final LicensesBean licensesBean = (LicensesBean) response.getEntity();

        assertEquals(licensesBean, beanToReturn);
    }
}
