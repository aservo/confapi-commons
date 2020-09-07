package de.aservo.confapi.commons.filter;

import com.sun.jersey.spi.container.ContainerRequest;
import com.sun.jersey.spi.container.ContainerRequestFilter;
import com.sun.jersey.spi.container.ContainerResponseFilter;
import com.sun.jersey.spi.container.ResourceFilter;
import de.aservo.confapi.commons.exception.ForbiddenException;
import de.aservo.confapi.commons.exception.UnauthorizedException;

import javax.annotation.Nullable;

public abstract class AbstractSysAdminOnlyResourceFilter<U> implements ResourceFilter, ContainerRequestFilter {

    @Override
    public ContainerRequest filter(ContainerRequest containerRequest) {
        final U loggedInUser = getLoggedInUser();
        if (loggedInUser == null) {
            throw new UnauthorizedException("Client must be logged in as an system administrator to access this resource.");
        } else if (!isSystemAdministrator(loggedInUser)) {
            throw new ForbiddenException("Client must be authenticated as an system administrator to access this resource.");
        }
        return containerRequest;
    }

    @Override
    public ContainerRequestFilter getRequestFilter() {
        return this;
    }

    @Override
    public ContainerResponseFilter getResponseFilter() {
        return null;
    }

    @Nullable
    public abstract U getLoggedInUser();

    public abstract boolean isSystemAdministrator(U user);

}
