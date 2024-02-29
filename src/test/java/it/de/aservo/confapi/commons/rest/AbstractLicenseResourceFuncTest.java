package it.de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.LicenseBean;
import de.aservo.confapi.commons.model.LicensesBean;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;

import java.util.Collection;

import static org.junit.Assert.*;

public abstract class AbstractLicenseResourceFuncTest {

    @Test
    public void testGetLicenses() {
        // Build the request for the licenses resource
        final Invocation.Builder licenseResource = ResourceBuilder.builder(ConfAPI.LICENSES).build();

        // Execute the GET request and receive the response
        final Response response = licenseResource.get();

        // Check the HTTP status code of the response
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());

        // Extract the entity (LicensesBean) from the response and get the licenses collection
        final LicensesBean licensesBean = response.readEntity(LicensesBean.class);
        final Collection<LicenseBean> licenses = licensesBean.getLicenses();

        // Perform your assertions
        assertNotNull(licenses);
        assertNotEquals(0, licenses.size());
        assertNotNull(licenses.iterator().next().getOrganization());
    }

    @Test
    public void testSetLicenses() {
        final Invocation.Builder licensesResource = ResourceBuilder.builder(ConfAPI.LICENSES).build();

        try (final Response response = licensesResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON_TYPE))) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        }

        final Response response = licensesResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(getExampleBean().getLicenses().iterator().next().getDescription(),
                response.readEntity(LicensesBean.class).getLicenses().iterator().next().getDescription());
    }

    @Test
    public void testAddLicenses() {
        final Invocation.Builder licensesResource = ResourceBuilder.builder(ConfAPI.LICENSES).build();
        final LicenseBean licenseBean = getExampleBean().getLicenses().iterator().next();

        try (final Response response = licensesResource.post(Entity.entity(licenseBean, MediaType.APPLICATION_JSON_TYPE))) {
            assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        }

        final Response response = licensesResource.get();
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertEquals(licenseBean.getDescription(),
                response.readEntity(LicensesBean.class).getLicenses().iterator().next().getDescription());
    }

    @Test
    public void testGetLicensesUnauthenticated() {
        final Invocation.Builder licensesResource = ResourceBuilder.builder(ConfAPI.LICENSES)
                .username("wrong")
                .password("password")
                .build();

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), licensesResource.get().getStatus());
    }

    @Test
    public void testSetLicensesUnauthenticated() {
        final Invocation.Builder licensesResource = ResourceBuilder.builder(ConfAPI.LICENSES)
                .username("wrong")
                .password("password")
                .build();

        final int status;
        try (final Response response = licensesResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON_TYPE))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), status);
    }

    @Test
    public void testGetLicensesUnauthorized() {
        final Invocation.Builder licensesResource = ResourceBuilder.builder(ConfAPI.LICENSES)
                .username("user")
                .password("user")
                .build();

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), licensesResource.get().getStatus());
    }

    @Test
    public void testSetLicensesUnauthorized() {
        final Invocation.Builder licensesResource = ResourceBuilder.builder(ConfAPI.LICENSES)
                .username("user")
                .password("user")
                .build();

        final int status;
        try (final Response response = licensesResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON_TYPE))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), status);
    }

    protected LicensesBean getExampleBean() {
        return LicensesBean.EXAMPLE_2_DEVELOPER_LICENSE;
    }
}
