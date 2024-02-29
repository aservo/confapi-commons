package it.de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.MailServerSmtpBean;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public abstract class AbstractMailServerSmtpResourceFuncTest {

    @Test
    public void testGetMailServerSmtp() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP).build();

        final Response response = mailServerResource.get();
        final MailServerSmtpBean mailServerSmtpBean = response.readEntity(MailServerSmtpBean.class);
        assertEquals(Response.Status.OK.getStatusCode(), response.getStatus());
        assertNotNull(mailServerSmtpBean);
    }

    @Test
    public void testSetMailServerSmtp() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP).build();

        final int status;
        final MailServerSmtpBean mailServerSmtpBean;
        try (final Response response = mailServerResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON))) {
            status = response.getStatus();
            mailServerSmtpBean = response.readEntity(MailServerSmtpBean.class);
        }
        assertEquals(Response.Status.OK.getStatusCode(), status);
        assertMailServerBeanAgainstExample(mailServerSmtpBean);
    }

    @Test // Unauthorized
    public void testGetMailServerSmtpUnauthenticated() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP)
                .username("wrong")
                .password("password")
                .build();

        final Response response = mailServerResource.get();
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), response.getStatus());
    }

    @Test // Unauthorized
    public void testSetMailServerSmtpUnauthenticated() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP)
                .username("wrong")
                .password("password")
                .build();

        final int status;
        try (final Response response = mailServerResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.UNAUTHORIZED.getStatusCode(), status);
    }

    @Test
    public void testGetMailServerSmtpUnauthorized() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP)
                .username("user")
                .password("user")
                .build();

        final Response response = mailServerResource.get();
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), response.getStatus());
    }

    @Test
    public void testSetMailServerSmtpUnauthorized() {
        final Invocation.Builder mailServerResource = ResourceBuilder.builder(ConfAPI.MAIL_SERVER + "/" + ConfAPI.MAIL_SERVER_SMTP)
                .username("user")
                .password("user")
                .build();

        final int status;
        try (final Response response = mailServerResource.put(Entity.entity(getExampleBean(), MediaType.APPLICATION_JSON))) {
            status = response.getStatus();
        }
        assertEquals(Response.Status.FORBIDDEN.getStatusCode(), status);
    }

    protected void assertMailServerBeanAgainstExample(MailServerSmtpBean bean) {
        final MailServerSmtpBean exampleBean = getExampleBean();
        // Reset the example password manually as it's excluded from equals check
        exampleBean.setPassword(null);
        assertEquals(exampleBean, bean);
    }

    protected MailServerSmtpBean getExampleBean() {
        return MailServerSmtpBean.EXAMPLE_2;
    }
}
