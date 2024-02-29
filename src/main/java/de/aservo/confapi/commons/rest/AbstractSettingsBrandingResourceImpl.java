package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.SettingsBrandingColorSchemeBean;
import de.aservo.confapi.commons.rest.api.SettingsBrandingResource;
import de.aservo.confapi.commons.service.api.SettingsBrandingService;

import jakarta.ws.rs.core.Response;
import java.io.InputStream;

public abstract class AbstractSettingsBrandingResourceImpl implements SettingsBrandingResource {

    private final SettingsBrandingService brandingService;

    public AbstractSettingsBrandingResourceImpl(final SettingsBrandingService brandingService) {
        this.brandingService = brandingService;
    }

    @Override
    public Response getBrandingColorScheme() {
        final SettingsBrandingColorSchemeBean colourSchemeBean = brandingService.getColourScheme();
        return Response.ok(colourSchemeBean).build();
    }

    @Override
    public Response setBrandingColorScheme(SettingsBrandingColorSchemeBean bean) {
        final SettingsBrandingColorSchemeBean colourSchemeBean = brandingService.setColourScheme(bean);
        return Response.ok(colourSchemeBean).build();
    }

    @Override
    public Response getBrandingLogo() {
        InputStream logo = brandingService.getLogo();
        return Response.ok(logo).build();
    }

    @Override
    public Response setBrandingLogo(InputStream binaryInputStream) {
        brandingService.setLogo(binaryInputStream);
        return Response.ok().build();
    }

    @Override
    public Response getBrandingFavicon() {
        InputStream favicon = brandingService.getFavicon();
        return Response.ok(favicon).build();
    }

    @Override
    public Response setBrandingFavicon(InputStream binaryInputStream) {
        brandingService.setFavicon(binaryInputStream);
        return Response.ok().build();
    }
}
