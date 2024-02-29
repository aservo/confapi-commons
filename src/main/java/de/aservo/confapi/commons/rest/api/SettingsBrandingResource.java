package de.aservo.confapi.commons.rest.api;

import de.aservo.confapi.commons.constants.ConfAPI;
import de.aservo.confapi.commons.model.SettingsBrandingColorSchemeBean;
import de.aservo.confapi.commons.model.ErrorCollection;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;

import jakarta.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.InputStream;

public interface SettingsBrandingResource {

    @GET
    @Path(ConfAPI.COLOR_SCHEME)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.SETTINGS },
            summary = "Get the color scheme",
            responses = {
                    @ApiResponse(
                            responseCode = "200", content = @Content(schema = @Schema(implementation = SettingsBrandingColorSchemeBean.class)),
                            description = "Returns the color scheme"
                    ),
                    @ApiResponse(
                            responseCode = "default", content = @Content(schema = @Schema(implementation = ErrorCollection.class)),
                            description = "Returns a list of error messages."
                    ),
            }
    )
    Response getBrandingColorScheme();

    @PUT
    @Path(ConfAPI.COLOR_SCHEME)
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.SETTINGS },
            summary = "Set the color scheme",
            responses = {
                    @ApiResponse(
                            responseCode = "200", content = @Content(schema = @Schema(implementation = SettingsBrandingColorSchemeBean.class)),
                            description = "Returns the updated color scheme"
                    ),
                    @ApiResponse(
                            responseCode = "default", content = @Content(schema = @Schema(implementation = ErrorCollection.class)),
                            description = "Returns a list of error messages."
                    ),
            }
    )
    Response setBrandingColorScheme(
            @NotNull final SettingsBrandingColorSchemeBean bean);

    @GET
    @Path(ConfAPI.LOGO)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(
            tags = { ConfAPI.SETTINGS },
            summary = "Get the logo",
            responses = {
                    @ApiResponse(
                            responseCode = "200", content = @Content(schema = @Schema(implementation = InputStream.class)),
                            description = "Returns the logo binary"
                    ),
                    @ApiResponse(
                            responseCode = "default", content = @Content(schema = @Schema(implementation = ErrorCollection.class)),
                            description = "Returns a list of error messages."
                    ),
            }
    )
    Response getBrandingLogo();

    @PUT
    @Path(ConfAPI.LOGO)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.SETTINGS },
            summary = "Set the logo",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "logo successfully set"
                    ),
                    @ApiResponse(
                            responseCode = "default", content = @Content(schema = @Schema(implementation = ErrorCollection.class)),
                            description = "Returns a list of error messages."
                    ),
            }
    )
    Response setBrandingLogo(
            @NotNull final InputStream binaryInputStream);

    @GET
    @Path(ConfAPI.FAVICON)
    @Produces(MediaType.APPLICATION_OCTET_STREAM)
    @Operation(
            tags = { ConfAPI.SETTINGS },
            summary = "Get the favicon",
            responses = {
                    @ApiResponse(
                            responseCode = "200", content = @Content(schema = @Schema(implementation = InputStream.class)),
                            description = "Returns the favicon binary"
                    ),
                    @ApiResponse(
                            responseCode = "default", content = @Content(schema = @Schema(implementation = ErrorCollection.class)),
                            description = "Returns a list of error messages."
                    ),
            }
    )
    Response getBrandingFavicon();

    @PUT
    @Path(ConfAPI.FAVICON)
    @Consumes(MediaType.APPLICATION_OCTET_STREAM)
    @Produces(MediaType.APPLICATION_JSON)
    @Operation(
            tags = { ConfAPI.SETTINGS },
            summary = "Set the favicon",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "favicon successfully set"
                    ),
                    @ApiResponse(
                            responseCode = "default", content = @Content(schema = @Schema(implementation = ErrorCollection.class)),
                            description = "Returns a list of error messages."
                    ),
            }
    )
    Response setBrandingFavicon(
            @NotNull final InputStream binaryInputStream);
}
