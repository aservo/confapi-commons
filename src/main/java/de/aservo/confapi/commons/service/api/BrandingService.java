package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.SettingsBrandingColourSchemeBean;

import javax.validation.constraints.NotNull;
import java.io.InputStream;

public interface BrandingService {

    /**
     * Get the colour scheme.
     *
     * @return the colour scheme
     */
    SettingsBrandingColourSchemeBean getColourScheme();


    /**
     * Set the colour scheme
     *
     * @param colourSchemeBean the colour scheme to set
     * @return the updated colour scheme
     */
    SettingsBrandingColourSchemeBean setColourScheme(
            @NotNull final SettingsBrandingColourSchemeBean colourSchemeBean);

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
