package it.de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.MailServerPopBean;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public abstract class AbstractMailServerPopResourceFuncTest {

    @Test
    public void testGetMailServerPop() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_POP).build();

        final Response response = mailServerResource.get();
        final MailServerPopBean mailServerPopBean = response.readEntity(MailServerPopBean.class);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(mailServerPopBean);
    }

    @Test
    public void testSetMailServerPop() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_POP).build();

        final int status;
        final MailServerPopBean mailServerPopBean;
        try (final Response response = mailServerResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON_TYPE))) {
            status = response.getStatus();
            mailServerPopBean = response.readEntity(MailServerPopBean.class);
        }
        assertEquals(Response.Status.OK.getStatusCode(), status);
        assertMailServerBeanAgainstExample(mailServerPopBean);
    }

    @Test
    public void testGetMailServerPopUnauthenticated() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_POP)
                .username("wrong")
                .password("password")
                .build();

        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), mailServerResource.get().getStatus());
    }

    @Test
    public void testSetMailServerPopUnauthenticated() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_POP)
                .username("wrong")
                .password("password")
                .build();

        final int status;
        try (final Response response = mailServerResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON_TYPE))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), status);
    }

    @Test
    public void testGetMailServerPopUnauthorized() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_POP)
                .username("user")
                .password("user")
                .build();

        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), mailServerResource.get().getStatus());
    }

    @Test
    public void testSetMailServerPopUnauthorized() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_POP)
                .username("user")
                .password("user")
                .build();

        final int status;
        try (final Response response = mailServerResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON_TYPE))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), status);
    }

    protected void assertMailServerBeanAgainstExample(MailServerPopBean bean) {
        final MailServerPopBean exampleBean = getExampleBean();
        //although field 'password' in 'AbstractMailServerProtocolBean' is annotated with '@EqualsExclude' equals still yields false if
        //not the same. Thus, we need to reset the example password manually
        exampleBean.setPassword(null);
        assertEquals(exampleBean, bean);
    }

    protected MailServerPopBean getExampleBean() {
        return MailServerPopBean.EXAMPLE_2;
    }
}
