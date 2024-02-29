package it.de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.SettingsBean;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractSettingsResourceFuncTest {

    @Test
    public void testGetSettings() {
        final Invocation.Builder settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS).build();
        final Response response = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(response.readEntity(SettingsBean.class).getTitle());
    }

    @Test
    public void testSetSettings() {
        final Invocation.Builder settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS).build();
        try (final Response response = settingsResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON))) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        }

        final Response response = settingsResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(getExampleBean(), response.readEntity(SettingsBean.class));
    }

    @Test // Unauthorized
    public void testGetSettingsUnauthenticated() {
        final Invocation.Builder settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS)
                .username("wrong")
                .password("password")
                .build();

        final int status;
        try (final Response response = settingsResource.get()) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), status);
    }

    @Test // Unauthorized
    public void testSetSettingsUnauthenticated() {
        final Invocation.Builder settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS)
                .username("wrong")
                .password("password")
                .build();

        final int status;
        try (final Response response = settingsResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), status);
    }

    @Test
    public void testGetSettingsUnauthorized() {
        final Invocation.Builder settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS)
                .username("user")
                .password("user")
                .build();

        final int status;
        try (final Response response = settingsResource.get()) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), status);
    }

    @Test
    public void testSetSettingsUnauthorized() {
        final Invocation.Builder settingsResource = ResourceBuilder.builder(ConfAPI.SETTINGS)
                .username("user")
                .password("user")
                .build();

        final int status;
        try (final Response response = settingsResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), status);
    }

    protected SettingsBean getExampleBean() {
        return SettingsBean.EXAMPLE_1;
    }
}
