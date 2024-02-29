package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.ApplicationLinkBean;
import de.aservo.confapi.commons.model.ApplicationLinksBean;
import de.aservo.confapi.commons.rest.api.ApplicationLinksResource;
import de.aservo.confapi.commons.service.api.ApplicationLinksService;
import jakarta.ws.rs.core.Response;

import java.util.UUID;

public abstract class AbstractApplicationLinksResourceImpl implements ApplicationLinksResource {

    private final ApplicationLinksService applicationLinksService;

    public AbstractApplicationLinksResourceImpl(
            final ApplicationLinksService applicationLinksService) {

        this.applicationLinksService = applicationLinksService;
    }

    @Override
    public Response getApplicationLinks() {
        final ApplicationLinksBean linksBean = applicationLinksService.getApplicationLinks();
        return Response.ok(linksBean).build();
    }

    @Override
    public Response getApplicationLink(
            final UUID uuid) {

        final ApplicationLinkBean linkBean = applicationLinksService.getApplicationLink(uuid);
        return Response.ok(linkBean).build();
    }

    @Override
    public Response setApplicationLinks(
            final boolean ignoreSetupErrors,
            ApplicationLinksBean linksBean) {

        final ApplicationLinksBean updatedLinksBean = applicationLinksService.setApplicationLinks(
                linksBean, ignoreSetupErrors);
        return Response.ok(updatedLinksBean).build();
    }

    @Override
    public Response setApplicationLink(
            final UUID uuid,
            final boolean ignoreSetupErrors,
            final ApplicationLinkBean linkBean) {

        final ApplicationLinkBean updatedLinkBean = applicationLinksService.setApplicationLink(
                uuid, linkBean, ignoreSetupErrors);
        return Response.ok(updatedLinkBean).build();
    }

    @Override
    public Response addApplicationLink(
            final boolean ignoreSetupErrors,
            final ApplicationLinkBean linkBean) {

        final ApplicationLinkBean addedApplicationLink = applicationLinksService.addApplicationLink(
                linkBean, ignoreSetupErrors);
        return Response.ok(addedApplicationLink).build();
    }

    @Override
    public Response deleteApplicationLinks(
            final boolean force) {

        applicationLinksService.deleteApplicationLinks(force);
        return Response.ok().build();
    }

    @Override
    public Response deleteApplicationLink(
            final UUID uuid) {

        applicationLinksService.deleteApplicationLink(uuid);
        return Response.ok().build();
    }
}
