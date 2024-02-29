package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.SettingsBean;

import jakarta.validation.constraints.NotNull;

public interface SettingsService {

    /**
     * Get the settings.
     *
     * @return the settings
     */
    SettingsBean getSettings();


    /**
     * Set the settings
     *
     * @param settingsBean the settings to set
     * @return the settings
     */
    SettingsBean setSettings(
            @NotNull final SettingsBean settingsBean);
}
