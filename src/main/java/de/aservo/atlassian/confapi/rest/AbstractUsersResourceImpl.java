package de.aservo.atlassian.confapi.rest;

import de.aservo.atlassian.confapi.exception.api.AbstractWebException;
import de.aservo.atlassian.confapi.model.ErrorCollection;
import de.aservo.atlassian.confapi.model.UserBean;
import de.aservo.atlassian.confapi.rest.api.UsersResource;
import de.aservo.atlassian.confapi.service.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;

public class AbstractUsersResourceImpl implements UsersResource {

    private static final Logger log = LoggerFactory.getLogger(AbstractUsersResourceImpl.class);

    private final UserService userService;

    public AbstractUsersResourceImpl(final UserService userService) {
        this.userService = userService;
    }

    @Override
    public Response getUser(
            final String userName) {

        final ErrorCollection errorCollection = new ErrorCollection();
        try {
            final UserBean userBean = userService.getUser(userName);
            return Response.ok(userBean).build();
        } catch (AbstractWebException e) {
            log.error(e.getMessage(), e);
            errorCollection.addErrorMessage(e.getMessage());
            return Response.status(e.getStatus()).entity(errorCollection).build();
        }
    }

    @Override
    public Response updateUser(
            final String userName,
            final UserBean userBean) {

        final ErrorCollection errorCollection = new ErrorCollection();
        try {
            final UserBean updatedUserBean = userService.updateUser(userName, userBean);
            return Response.ok(updatedUserBean).build();
        } catch (AbstractWebException e) {
            log.error(e.getMessage(), e);
            errorCollection.addErrorMessage(e.getMessage());
            return Response.status(e.getStatus()).entity(errorCollection).build();
        }
    }

    @Override
    public Response setUserPassword(
            final String userName,
            final String password) {

        final ErrorCollection errorCollection = new ErrorCollection();
        try {
            final UserBean updatedUserBean = userService.updatePassword(userName, password);
            return Response.ok(updatedUserBean).build();
        } catch (AbstractWebException e) {
            log.error(e.getMessage(), e);
            errorCollection.addErrorMessage(e.getMessage());
            return Response.status(e.getStatus()).entity(errorCollection).build();
        }
    }

}
