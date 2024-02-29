package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.MailServerPopBean;
import de.aservo.confapi.commons.model.MailServerSmtpBean;
import de.aservo.confapi.commons.rest.api.MailServerResource;
import de.aservo.confapi.commons.service.api.MailServerService;

import jakarta.ws.rs.core.Response;

public class AbstractMailServerResourceImpl implements MailServerResource {

    private final MailServerService mailServerService;

    public AbstractMailServerResourceImpl(
            final MailServerService mailServerService) {
        this.mailServerService = mailServerService;
    }

    @Override
    public Response getMailServerSmtp() {
        final MailServerSmtpBean smtpBean = mailServerService.getMailServerSmtp();
        return Response.ok(smtpBean).build();
    }

    @Override
    public Response setMailServerSmtp(MailServerSmtpBean bean) {
        final MailServerSmtpBean updatedSmtpBean = mailServerService.setMailServerSmtp(bean);
        return Response.ok(updatedSmtpBean).build();
    }

    @Override
    public Response getMailServerPop() {
        final MailServerPopBean popBean = mailServerService.getMailServerPop();
        return Response.ok(popBean).build();
    }

    @Override
    public Response setMailServerPop(MailServerPopBean bean) {
        final MailServerPopBean updatedPopBean = mailServerService.setMailServerPop(bean);
        return Response.ok(updatedPopBean).build();
    }
}
