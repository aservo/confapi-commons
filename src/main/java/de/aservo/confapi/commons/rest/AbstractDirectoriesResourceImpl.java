package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.AbstractDirectoryBean;
import de.aservo.confapi.commons.model.DirectoriesBean;
import de.aservo.confapi.commons.rest.api.DirectoriesResource;
import de.aservo.confapi.commons.service.api.DirectoriesService;

import javax.ws.rs.core.Response;

public abstract class AbstractDirectoriesResourceImpl implements DirectoriesResource {

    private final DirectoriesService directoriesService;

    public AbstractDirectoriesResourceImpl(DirectoriesService directoriesService) {
        this.directoriesService = directoriesService;
    }

    @Override
    public Response getDirectories() {
        final DirectoriesBean directoriesBean = directoriesService.getDirectories();
        return Response.ok(directoriesBean).build();
    }

    @Override
    public Response getDirectory(
            final long id) {
        final AbstractDirectoryBean directoryBean = directoriesService.getDirectory(id);
        return Response.ok(directoryBean).build();
    }

    @Override
    public Response setDirectories (
            final boolean testConnection,
            final DirectoriesBean directories) {

        DirectoriesBean directoriesBean = directoriesService.setDirectories(directories, testConnection);
        return Response.ok(directoriesBean).build();
    }

    @Override
    public Response setDirectory (
            final long id,
            final boolean testConnection,
            final AbstractDirectoryBean directory) {

        AbstractDirectoryBean directoriesBean = directoriesService.setDirectory(id, directory, testConnection);
        return Response.ok(directoriesBean).build();
    }

    @Override
    public Response addDirectory (
            final boolean testConnection,
            final AbstractDirectoryBean directory) {

        AbstractDirectoryBean addedDirectoryBean = directoriesService.addDirectory(directory, testConnection);
        return Response.ok(addedDirectoryBean).build();
    }

    @Override
    public Response deleteDirectories(
            final boolean force) {
        directoriesService.deleteDirectories(force);
        return Response.ok().build();
    }

    @Override
    public Response deleteDirectory(
            final long id) {
        directoriesService.deleteDirectory(id);
        return Response.ok().build();
    }
}
