package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.AbstractDirectoryBean;
import de.aservo.confapi.commons.model.DirectoriesBean;

import jakarta.validation.constraints.NotNull;

/**
 * The User directory service interface.
 */
public interface DirectoriesService {

    /**
     * Gets all directories.
     *
     * @return the directories
     */
    DirectoriesBean getDirectories();

    /**
     * Gets a single directory.
     *
     * @param id the directory id to query
     * @return the directory
     */
    AbstractDirectoryBean getDirectory(
            final long id);

    /**
     * Adds or Updates directory configurations. Any existing configurations with the same 'name' property is updated.
     *
     * @param directories    the directories
     * @param testConnection whether to test connection
     * @return the directories
     */
    DirectoriesBean setDirectories(
            @NotNull DirectoriesBean directories,
            boolean testConnection);

    /**
     * Updates a single directory configuration. Any existing configuration with the same 'name' property is updated.
     *
     * @param id             the directory id to update
     * @param directory      the directory
     * @param testConnection whether to test connection
     * @return the directories
     */
    AbstractDirectoryBean setDirectory(
            long id,
            @NotNull AbstractDirectoryBean directory,
            boolean testConnection);

    /**
     * Adds a new directory configuration.
     *
     * @param directory      the directories
     * @param testConnection whether to test connection
     * @return the added directory
     */
    AbstractDirectoryBean addDirectory(
            @NotNull AbstractDirectoryBean directory,
            boolean testConnection);

    /**
     * Deletes all directories
     *
     * @param force must be set to 'true' in order to delete all entries
     */
    void deleteDirectories(
            boolean force);

    /**
     * Deletes a single directory
     *
     * @param id the directory id to delete
     */
    void deleteDirectory(
            final long id);
}
