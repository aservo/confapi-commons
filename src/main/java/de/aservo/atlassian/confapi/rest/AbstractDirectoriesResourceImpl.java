package de.aservo.atlassian.confapi.rest;

import de.aservo.atlassian.confapi.exception.api.AbstractWebException;
import de.aservo.atlassian.confapi.model.DirectoriesBean;
import de.aservo.atlassian.confapi.model.DirectoryBean;
import de.aservo.atlassian.confapi.rest.api.DirectoriesResource;
import de.aservo.atlassian.confapi.service.api.DirectoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Nonnull;
import javax.ws.rs.core.Response;

import static com.google.common.base.Preconditions.checkNotNull;

public abstract class AbstractDirectoriesResourceImpl implements DirectoriesResource {

    private static final Logger log = LoggerFactory.getLogger(AbstractDirectoriesResourceImpl.class);

    private final DirectoryService directoryService;

    public AbstractDirectoriesResourceImpl(DirectoryService directoryService) {
        this.directoryService = checkNotNull(directoryService);
    }

    @Override
    public Response getDirectories() {
        final DirectoriesBean directoriesBean = new DirectoriesBean(directoryService.getDirectories());
        return Response.ok(directoriesBean).build();
    }

    @Override
    public Response setDirectory(
            final boolean testConnection,
            @Nonnull final DirectoryBean directory) {

        try {
            DirectoryBean addDirectory = directoryService.setDirectory(directory, testConnection);
            return Response.ok(addDirectory).build();
        } catch (AbstractWebException e) {
            log.error(e.getMessage(), e);
            return e.getResponse();
        }
    }

}
