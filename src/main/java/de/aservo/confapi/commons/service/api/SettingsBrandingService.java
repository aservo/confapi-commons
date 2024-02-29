package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.SettingsBrandingColorSchemeBean;

import jakarta.validation.constraints.NotNull;
import java.io.InputStream;

public interface SettingsBrandingService {

    /**
     * Get the colour scheme.
     *
     * @return the colour scheme
     */
    SettingsBrandingColorSchemeBean getColourScheme();


    /**
     * Set the colour scheme
     *
     * @param colourSchemeBean the colour scheme to set
     * @return the updated colour scheme
     */
    SettingsBrandingColorSchemeBean setColourScheme(
            @NotNull final SettingsBrandingColorSchemeBean colourSchemeBean);

    /**
     * Get the logo binary.
     *
     * @return the logo
     */
    InputStream getLogo();

    /**
     * Set the logo
     *
     * @param logoBinary the logo to set
     */
    void setLogo(
            @NotNull InputStream logoBinary);

    /**
     * Get the favicon binary.
     *
     * @return the favicon
     */
    InputStream getFavicon();

    /**
     * Set the favicon
     *
     * @param faviconBinary the favicon to set
     */
    void setFavicon(
            @NotNull InputStream faviconBinary);
}
