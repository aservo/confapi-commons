package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.GadgetBean;
import de.aservo.confapi.commons.model.GadgetsBean;
import de.aservo.confapi.commons.service.api.GadgetsService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AbstractGadgetsResourceTest {

    @Mock
    private GadgetsService gadgetsService;

    private TestGadgetsResourceImpl resource;

    @Before
    public void setup() {
        resource = new TestGadgetsResourceImpl(gadgetsService);
    }

    @Test
    public void testGetGadgets() {
        final GadgetsBean bean = GadgetsBean.EXAMPLE_1;

        doReturn(bean).when(gadgetsService).getGadgets();

        final Response response = resource.getGadgets();
        assertEquals(200, response.getStatus());
        final GadgetsBean gadgetsBean = (GadgetsBean) response.getEntity();

        assertEquals(gadgetsBean, bean);
    }

    @Test
    public void testGetGadget() {
        final GadgetBean bean = GadgetBean.EXAMPLE_1;

        doReturn(bean).when(gadgetsService).getGadget(1L);

        final Response response = resource.getGadget(1L);
        assertEquals(200, response.getStatus());
        final GadgetBean gadgetBean = (GadgetBean) response.getEntity();

        assertEquals(gadgetBean, bean);
    }

    @Test
    public void testSetGadgets() {
        final GadgetsBean bean = GadgetsBean.EXAMPLE_1;

        doReturn(bean).when(gadgetsService).setGadgets(bean);

        final Response response = resource.setGadgets(bean);
        assertEquals(200, response.getStatus());
        final GadgetsBean gadgetsBean = (GadgetsBean) response.getEntity();

        assertEquals(gadgetsBean, bean);
    }

    @Test
    public void testSetGadget() {
        final GadgetBean bean = GadgetBean.EXAMPLE_1;

        doReturn(bean).when(gadgetsService).setGadget(1L, bean);

        final Response response = resource.setGadget(1L, bean);
        assertEquals(200, response.getStatus());
        final GadgetBean gadgetBean = (GadgetBean) response.getEntity();

        assertEquals(gadgetBean, bean);
    }

    @Test
    public void testAddGadget() {
        final GadgetBean bean = GadgetBean.EXAMPLE_1;

        doReturn(bean).when(gadgetsService).addGadget(bean);

        final Response response = resource.addGadget(bean);
        assertEquals(200, response.getStatus());
        final GadgetBean responseBean = (GadgetBean) response.getEntity();

        assertEquals(bean, responseBean);
    }

    @Test
    public void testDeleteGadgets() {
        resource.deleteGadgets(true);
        assertTrue("Delete Successful", true);
    }

    @Test
    public void testDeleteGadget() {
        resource.deleteGadget(1L);
        assertTrue("Delete Successful", true);
    }
}
