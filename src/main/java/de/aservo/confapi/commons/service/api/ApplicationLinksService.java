package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.ApplicationLinkBean;
import de.aservo.confapi.commons.model.ApplicationLinksBean;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public interface ApplicationLinksService {

    /**
     * Gets all application links.
     *
     * @return the application links
     */
    ApplicationLinksBean getApplicationLinks();

    /**
     * Gets a single application link.
     *
     * @param uuid the application link uuid query
     * @return the application link
     */
    ApplicationLinkBean getApplicationLink(
            final UUID uuid);

    /**
     * Sets or updates the given application links
     *
     * @param applicationLinksBean the application links to set / update
     * @param ignoreSetupErrors    whether or not to ignore authentication or other setup errors when setting up the link
     *                             which usually happens if the application to link has not setup the link counterpart yet
     * @return the updated application links
     */
    ApplicationLinksBean setApplicationLinks(
            @NotNull final ApplicationLinksBean applicationLinksBean,
            boolean ignoreSetupErrors);

    /**
     * Updates the given application link
     *
     * @param uuid                the application link uuid to update
     * @param applicationLinkBean the application link to set / update
     * @param ignoreSetupErrors   whether or not to ignore authentication or other setup errors when setting up the link
     *                            which usually happens if the application to link has not setup the link counterpart yet
     * @return the updated application link
     */
    ApplicationLinkBean setApplicationLink(
            @NotNull final UUID uuid,
            @NotNull final ApplicationLinkBean applicationLinkBean,
            boolean ignoreSetupErrors);

    /**
     * Adds a single application link
     *
     * @param applicationLinkBean the application link to set
     * @param ignoreSetupErrors   whether or not to ignore authentication or other setup errors when setting up the link
     *                            which usually happens if the application to link has not setup the link counterpart yet
     * @return the added application link
     */
    ApplicationLinkBean addApplicationLink(
            @NotNull final ApplicationLinkBean applicationLinkBean,
            boolean ignoreSetupErrors);

    /**
     * Deletes all application links
     *
     * @param force must be set to 'true' in order to delete all entries
     */
    void deleteApplicationLinks(
            boolean force);

    /**
     * Deletes a single application link
     *
     * @param uuid the application link uuid to delete
     */
    void deleteApplicationLink(
            @NotNull final UUID uuid);

}
