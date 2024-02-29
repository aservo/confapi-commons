package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.SettingsBean;
import de.aservo.confapi.commons.rest.api.SettingsResource;
import de.aservo.confapi.commons.service.api.SettingsService;

import jakarta.ws.rs.core.Response;

public abstract class AbstractSettingsResourceImpl implements SettingsResource {

    private final SettingsService settingsService;

    public AbstractSettingsResourceImpl(final SettingsService settingsService) {
        this.settingsService = settingsService;
    }

    @Override
    public Response getSettings() {
        final SettingsBean settingsBean = settingsService.getSettings();
        return Response.ok(settingsBean).build();
    }

    @Override
    public Response setSettings(SettingsBean settingsBean) {
        final SettingsBean updatedSettingsBean = settingsService.setSettings(settingsBean);
        return Response.ok(updatedSettingsBean).build();
    }
}
