package de.aservo.atlassian.confapi.helper;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public interface WebAuthenticationHelper<U> {

    U getAuthenticatedUser();

    boolean isSystemAdministrator(U user);

    default void mustBeSysAdmin() {
        final U user = getAuthenticatedUser();

        if (user == null) {
            // NOSONAR: Ignore that WebApplicationException is a RuntimeException
            throw new WebApplicationException(Response.Status.UNAUTHORIZED);
        }

        if (!isSystemAdministrator(user)) {
            // NOSONAR: Ignore that WebApplicationException is a RuntimeException
            throw new WebApplicationException(Response.Status.FORBIDDEN);
        }
    }

}
