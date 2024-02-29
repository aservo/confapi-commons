package it.de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public abstract class AbstractPingResourceFuncTest {

    @Test
    public void testGetPing() {
        final Invocation.Builder pingResource = ResourceBuilder.builder(ConfAPI.PING).acceptMediaType(MediaType.TEXT_PLAIN).build();
        assertEquals(Response.Status.OK.getStatusCode(), pingResource.get().getStatus());
    }
}
