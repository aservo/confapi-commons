package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.rest.api.PingResource;

import jakarta.ws.rs.core.Response;

public abstract class AbstractPingResourceImpl implements PingResource {

    @Override
    public Response getPing() {
        return Response.ok(PONG).build();
    }

}
