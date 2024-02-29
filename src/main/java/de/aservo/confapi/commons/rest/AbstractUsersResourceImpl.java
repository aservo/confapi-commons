package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.UserBean;
import de.aservo.confapi.commons.rest.api.UsersResource;
import de.aservo.confapi.commons.service.api.UsersService;

import jakarta.ws.rs.core.Response;

public class AbstractUsersResourceImpl implements UsersResource {

    private final UsersService usersService;

    public AbstractUsersResourceImpl(final UsersService usersService) {
        this.usersService = usersService;
    }

    @Override
    public Response getUser(
            final String username) {

        final UserBean userBean = usersService.getUser(username);
        return Response.ok(userBean).build();
    }

    @Override
    public Response setUser(
            final String username,
            final UserBean userBean) {

        final UserBean updatedUserBean = usersService.updateUser(username, userBean);
        return Response.ok(updatedUserBean).build();
    }

    @Override
    public Response setUserPassword(
            final String username,
            final String password) {

        final UserBean updatedUserBean = usersService.updatePassword(username, password);
        return Response.ok(updatedUserBean).build();
    }

}
