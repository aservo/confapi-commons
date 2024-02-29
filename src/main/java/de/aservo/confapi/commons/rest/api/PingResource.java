package de.aservo.confapi.commons.rest.api;

import de.aservo.confapi.commons.constants.ConfAPI;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

public interface PingResource {

    String PONG = "pong";

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    @Operation(
            tags = { ConfAPI.PING },
            summary = "Ping method for probing the REST API.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Returns '" + PONG + "'"
                    ),
            }
    )
    Response getPing();

}
