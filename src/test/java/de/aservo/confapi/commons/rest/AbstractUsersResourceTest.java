package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.UserBean;
import de.aservo.confapi.commons.service.api.UsersService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import jakarta.ws.rs.core.Response;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class AbstractUsersResourceTest {

    @Mock
    private UsersService usersService;

    private TestUsersResourceImpl resource;

    @Before
    public void setup() {
        resource = new TestUsersResourceImpl(usersService);
    }

    @Test
    public void testGetUser() {
        final UserBean bean = UserBean.EXAMPLE_1;

        doReturn(bean).when(usersService).getUser(bean.getUsername());

        final Response response = resource.getUser(bean.getUsername());
        assertEquals(200, response.getStatus());
        final UserBean userBean = (UserBean) response.getEntity();

        assertEquals(userBean, bean);
    }

    @Test
    public void testUpdateUser() {
        final UserBean bean = UserBean.EXAMPLE_1;

        doReturn(bean).when(usersService).updateUser(bean.getUsername(), bean);

        final Response response = resource.setUser(bean.getUsername(), bean);
        assertEquals(200, response.getStatus());
        final UserBean userBean = (UserBean) response.getEntity();

        assertEquals(userBean, bean);
    }

    @Test
    public void testUpdateUserPassword() {
        final UserBean bean = UserBean.EXAMPLE_1;

        doReturn(bean).when(usersService).updatePassword(bean.getUsername(), bean.getPassword());

        final Response response = resource.setUserPassword(bean.getUsername(), bean.getPassword());
        assertEquals(200, response.getStatus());
        final UserBean userBean = (UserBean) response.getEntity();

        assertEquals(userBean, bean);
    }

}
