package de.aservo.atlassian.confapi.helper;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.Spy;
import org.mockito.junit.MockitoJUnitRunner;

import javax.ws.rs.WebApplicationException;

import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class WebAuthenticationHelperTest {

    private final Object user = new Object();

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Spy
    private WebAuthenticationHelper<Object> webAuthenticationHelper = new WebAuthenticationHelper<Object>() {
        @Override
        public Object getAuthenticatedUser() {
            return null;
        }

        @Override
        public boolean isSystemAdministrator(Object user) {
            return false;
        }
    };

    @Test
    public void testNotAuthenticated() {
        when(webAuthenticationHelper.getAuthenticatedUser()).thenReturn(null);
        exceptionRule.expect(WebApplicationException.class);
        webAuthenticationHelper.mustBeSysAdmin();
    }

    @Test
    public void testNotSysAdmin() {
        when(webAuthenticationHelper.getAuthenticatedUser()).thenReturn(user);
        when(webAuthenticationHelper.isSystemAdministrator(user)).thenReturn(false);
        exceptionRule.expect(WebApplicationException.class);
        webAuthenticationHelper.mustBeSysAdmin();
    }

    @Test
    @SuppressWarnings("java:S2699") // Ignore that no assertion is present
    public void testIsSysAdmin() {
        when(webAuthenticationHelper.getAuthenticatedUser()).thenReturn(user);
        when(webAuthenticationHelper.isSystemAdministrator(user)).thenReturn(true);
        webAuthenticationHelper.mustBeSysAdmin();
    }

}
