package it.de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.UserBean;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractUserResourceFuncTest {

    private static final String PARAM_USERNAME = "username";

    @Test
    public void testGetUser() {
        final UserBean exampleBean = getExampleBean();
        final Invocation.Builder usersResource = ResourceBuilder.builder(ConfAPI.USERS + getUserNameQueryParam(exampleBean)).build();

        final Response response = usersResource.get();
        final UserBean userBean = response.readEntity(UserBean.class);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(userBean);
    }

    @Test
    public void testSetUserEmailAddress() {
        final UserBean exampleBean = getExampleBean();
        final Invocation.Builder usersResource = ResourceBuilder.builder(ConfAPI.USERS + getUserNameQueryParam(exampleBean)).build();

        final int status;
        final UserBean userBean;
        try (final Response response = usersResource.put(Entity.entity(exampleBean, MediaType.APPLICATION_JSON))) {
            status = response.getStatus();
            userBean = response.readEntity(UserBean.class);
        }
        assertEquals(Response.Status.OK.getStatusCode(), status);
        assertEquals(exampleBean.getEmail(), userBean.getEmail());
    }

    @Test
    public void testSetUserPassword() {
        final UserBean exampleBean = getExampleBean();
        final Invocation.Builder usersResource = ResourceBuilder
                .builder(ConfAPI.USERS + "/" + ConfAPI.USER_PASSWORD + getUserNameQueryParam(exampleBean))
                .contentMediaType(MediaType.TEXT_PLAIN)
                .build();

        final int status;
        try (final Response response = usersResource.put(Entity.text(exampleBean.getPassword()))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.OK.getStatusCode(), status);
    }

    @Test
    public void testGetUserUnauthenticated() {
        final UserBean exampleBean = getExampleBean();
        final Invocation.Builder usersResource = ResourceBuilder.builder(ConfAPI.USERS + getUserNameQueryParam(exampleBean))
                .username("wrong")
                .password("password")
                .build();

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), usersResource.get().getStatus());
    }

    @Test
    public void testSetUserEmailAddressUnauthenticated() {
        final UserBean exampleBean = getExampleBean();
        final Invocation.Builder usersResource = ResourceBuilder.builder(ConfAPI.USERS + getUserNameQueryParam(exampleBean))
                .username("wrong")
                .password("password")
                .build();

        final int status;
        try (final Response response = usersResource.put(Entity.entity(exampleBean, MediaType.APPLICATION_JSON))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), status);
    }

    @Test
    public void testGetUserUnauthorized() {
        final UserBean exampleBean = getExampleBean();
        final Invocation.Builder usersResource = ResourceBuilder.builder(ConfAPI.USERS + getUserNameQueryParam(exampleBean))
                .username("user")
                .password("user")
                .build();

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), usersResource.get().getStatus());
    }

    @Test
    public void testSetUserEmailAddressUnauthorized() {
        final UserBean exampleBean = getExampleBean();
        final Invocation.Builder usersResource = ResourceBuilder.builder(ConfAPI.USERS + getUserNameQueryParam(exampleBean))
                .username("user")
                .password("user")
                .build();

        final int status;
        try (final Response response = usersResource.put(Entity.entity(exampleBean, MediaType.APPLICATION_JSON))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), status);
    }

    protected String getUserNameQueryParam(UserBean userBean) {
        return "?" + PARAM_USERNAME + "=" + userBean.getUsername();
    }

    protected UserBean getExampleBean() {
        return UserBean.EXAMPLE_3_ADMIN;
    }
}
