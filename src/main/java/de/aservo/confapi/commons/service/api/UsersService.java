package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.UserBean;

import jakarta.validation.constraints.NotNull;
import java.util.Collection;
import java.util.List;

public interface UsersService {

    /**
     * Get the user.
     *
     * @param username the username
     * @return the user bean
     */
    UserBean getUser(
            @NotNull final String username);

    /**
     * Get the user.
     *
     * @param directoryId the directory id
     * @param username the username
     * @return the user bean
     */
    UserBean getUser(
            final long directoryId,
            @NotNull final String username);

    /**
     * Set (add or update) user.
     *
     * @param directoryId the directory id
     * @param userBean the user bean
     * @return the set user bean
     */
    UserBean setUser(
            final long directoryId,
            @NotNull final UserBean userBean);

    /**
     * Set (add or update) users.
     *
     * @param directoryId the directory id
     * @param userBeans the user beans
     * @return the set user beans
     */
    List<UserBean> setUsers(
            final long directoryId,
            @NotNull final Collection<UserBean> userBeans);

    /**
     * Update the user.
     *
     * @param username the username
     * @param userBean the user bean
     * @return the updated user bean
     */
    UserBean updateUser(
            @NotNull final String username,
            @NotNull final UserBean userBean);

    /**
     * Update the user password.
     *
     * @param username the username
     * @param password the password
     * @return the updated user bean
     */
    UserBean updatePassword(
            @NotNull final String username,
            @NotNull final String password);

}
