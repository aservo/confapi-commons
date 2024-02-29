package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.GadgetBean;
import de.aservo.confapi.commons.model.GadgetsBean;
import de.aservo.confapi.commons.service.api.GadgetsService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class AbstractGadgetsResourceTest {

    @Mock
    private GadgetsService gadgetsService;

    private TestGadgetsResourceImpl resource;

    @BeforeEach
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
        assertTrue(true, "Delete Successful");
    }

    @Test
    public void testDeleteGadget() {
        resource.deleteGadget(1L);
        assertTrue(true, "Delete Successful");
    }
}
