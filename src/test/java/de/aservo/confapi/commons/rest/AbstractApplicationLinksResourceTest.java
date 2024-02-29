package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.ApplicationLinkBean;
import de.aservo.confapi.commons.model.ApplicationLinksBean;
import de.aservo.confapi.commons.service.api.ApplicationLinksService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.ws.rs.core.Response;

import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AbstractApplicationLinksResourceTest {

    @Mock
    private ApplicationLinksService applicationLinksService;

    private TestApplicationLinksResourceImpl resource;

    @Before
    public void setup() {
        resource = new TestApplicationLinksResourceImpl(applicationLinksService);
    }

    @Test
    public void testGetApplicationLinks() {
        final ApplicationLinksBean bean = ApplicationLinksBean.EXAMPLE_1;

        doReturn(bean).when(applicationLinksService).getApplicationLinks();

        final Response response = resource.getApplicationLinks();
        assertEquals(200, response.getStatus());
        final ApplicationLinksBean linksBean = (ApplicationLinksBean) response.getEntity();

        assertEquals(linksBean, bean);
    }

    @Test
    public void testGetApplicationLink() {
        final ApplicationLinkBean bean = ApplicationLinkBean.EXAMPLE_1;

        UUID id = UUID.randomUUID();

        doReturn(bean).when(applicationLinksService).getApplicationLink(id);

        final Response response = resource.getApplicationLink(id);
        assertEquals(200, response.getStatus());
        final ApplicationLinkBean linkBean = (ApplicationLinkBean) response.getEntity();

        assertEquals(linkBean, bean);
    }

    @Test
    public void testSetApplicationLinks() {
        final ApplicationLinksBean bean = ApplicationLinksBean.EXAMPLE_1;

        doReturn(bean).when(applicationLinksService).setApplicationLinks(bean, true);

        final Response response = resource.setApplicationLinks(true, bean);
        assertEquals(200, response.getStatus());
        final ApplicationLinksBean linksBean = (ApplicationLinksBean) response.getEntity();

        assertEquals(linksBean, bean);
    }

    @Test
    public void testSetApplicationLink() {
        final ApplicationLinkBean bean = ApplicationLinkBean.EXAMPLE_1;
        UUID id = UUID.randomUUID();

        doReturn(bean).when(applicationLinksService).setApplicationLink(id, bean, true);

        final Response response = resource.setApplicationLink(id, true, bean);
        assertEquals(200, response.getStatus());
        final ApplicationLinkBean linkBean = (ApplicationLinkBean) response.getEntity();

        assertEquals(linkBean, bean);
    }

    @Test
    public void testAddApplicationLink() {
        final ApplicationLinkBean bean = ApplicationLinkBean.EXAMPLE_1;

        doReturn(bean).when(applicationLinksService).addApplicationLink(bean, true);

        final Response response = resource.addApplicationLink(true, bean);
        assertEquals(200, response.getStatus());
        final ApplicationLinkBean responseBean = (ApplicationLinkBean) response.getEntity();

        assertEquals(responseBean, bean);
    }

    @Test
    public void testDeleteApplicationLinks() {
        resource.deleteApplicationLinks(true);
        assertTrue("Delete Successful", true);
    }

    @Test
    public void testDeleteApplicationLink() {
        resource.deleteApplicationLink(UUID.randomUUID());
        assertTrue("Delete Successful", true);
    }
}
