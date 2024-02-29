package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.GadgetBean;
import de.aservo.confapi.commons.model.GadgetsBean;
import de.aservo.confapi.commons.rest.api.GadgetsResource;
import de.aservo.confapi.commons.service.api.GadgetsService;

import jakarta.ws.rs.core.Response;

public abstract class AbstractGadgetsResourceImpl implements GadgetsResource {

    private final GadgetsService gadgetsService;

    public AbstractGadgetsResourceImpl(final GadgetsService gadgetsService) {
        this.gadgetsService = gadgetsService;
    }

    @Override
    public Response getGadgets() {
        final GadgetsBean gadgetsBean = gadgetsService.getGadgets();
        return Response.ok(gadgetsBean).build();
    }

    @Override
    public Response getGadget(
            final long id) {
        final GadgetBean gadgetBean = gadgetsService.getGadget(id);
        return Response.ok(gadgetBean).build();
    }

    @Override
    public Response setGadgets(
            final GadgetsBean gadgetsBean) {
        GadgetsBean updatedGadgetsBean = gadgetsService.setGadgets(gadgetsBean);
        return Response.ok(updatedGadgetsBean).build();
    }

    @Override
    public Response setGadget(
            final long id,
            final GadgetBean gadgetBean) {
        GadgetBean updatedGadgetBean = gadgetsService.setGadget(
                id,
                gadgetBean);
        return Response.ok(updatedGadgetBean).build();
    }

    @Override
    public Response addGadget(
            final GadgetBean gadgetBean) {
        GadgetBean addedGadgetBean = gadgetsService.addGadget(gadgetBean);
        return Response.ok(addedGadgetBean).build();
    }

    @Override
    public Response deleteGadgets(
            final boolean force) {
        gadgetsService.deleteGadgets(force);
        return Response.ok().build();
    }

    @Override
    public Response deleteGadget(
            final long id) {
        gadgetsService.deleteGadget(id);
        return Response.ok().build();
    }
}
