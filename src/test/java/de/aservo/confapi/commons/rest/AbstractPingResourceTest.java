package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.rest.api.PingResource;
import jakarta.ws.rs.core.Response;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.CALLS_REAL_METHODS;
import static org.mockito.Mockito.mock;

@RunWith(MockitoJUnitRunner.class)
public class AbstractPingResourceTest {

    private AbstractPingResourceImpl pingResource;

    @Before
    public void setup() {
        // we can use mock with CALLS_REAL_METHODS here because PingResource uses no services
        pingResource = mock(AbstractPingResourceImpl.class, CALLS_REAL_METHODS);
    }

    @Test
    public void testGetPing() {
        final Response pingResponse = pingResource.getPing();
        Assert.assertEquals(PingResource.PONG, pingResponse.getEntity().toString());
    }

}
