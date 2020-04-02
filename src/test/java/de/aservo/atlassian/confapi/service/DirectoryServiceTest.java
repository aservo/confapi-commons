package de.aservo.atlassian.confapi.service;

import com.atlassian.crowd.embedded.api.CrowdDirectoryService;
import com.atlassian.crowd.embedded.api.Directory;
import com.atlassian.crowd.embedded.api.DirectoryType;
import com.atlassian.crowd.exception.DirectoryCurrentlySynchronisingException;
import com.atlassian.crowd.model.directory.ImmutableDirectory;
import de.aservo.atlassian.confapi.model.DirectoryBean;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.validation.ValidationException;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.atlassian.crowd.directory.RemoteCrowdDirectory.*;
import static com.atlassian.crowd.directory.SynchronisableDirectoryProperties.*;
import static com.atlassian.crowd.directory.SynchronisableDirectoryProperties.SyncGroupMembershipsAfterAuth.WHEN_AUTHENTICATION_CREATED_THE_USER;
import static com.atlassian.crowd.model.directory.DirectoryImpl.ATTRIBUTE_KEY_USE_NESTED_GROUPS;
import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@RunWith(MockitoJUnitRunner.class)
public class DirectoryServiceTest {

    @Mock
    private CrowdDirectoryService crowdDirectoryService;

    @InjectMocks
    private DirectoryServiceImpl userDirectoryService;

    @Test
    public void testGetDirectories() {
        Directory directory = createDirectory();
        doReturn(Collections.singletonList(directory)).when(crowdDirectoryService).findAllDirectories();

        List<DirectoryBean> directories = userDirectoryService.getDirectories();

        assertEquals(directories.get(0), DirectoryBean.from(directory));
    }

    @Test
    public void testAddDirectoryWithoutExistingDirectory() throws DirectoryCurrentlySynchronisingException {
        Directory directory = createDirectory();
        doReturn(directory).when(crowdDirectoryService).addDirectory(any(Directory.class));

        DirectoryBean directoryBean = DirectoryBean.from(directory);
        directoryBean.setAppPassword("test");
        DirectoryBean directoryAdded = userDirectoryService.addDirectory(directoryBean, false);

        assertEquals(directoryAdded.getName(), directoryBean.getName());
    }

    @Test
    public void testAddDirectoryWithExistingDirectory() throws DirectoryCurrentlySynchronisingException {
        Directory directory = createDirectory();
        doReturn(Collections.singletonList(directory)).when(crowdDirectoryService).findAllDirectories();
        doReturn(directory).when(crowdDirectoryService).addDirectory(any(Directory.class));

        DirectoryBean directoryBean = DirectoryBean.from(directory);
        directoryBean.setAppPassword("test");
        DirectoryBean directoryAdded = userDirectoryService.addDirectory(directoryBean, false);

        assertEquals(directoryAdded.getName(), directoryBean.getName());
    }

    @Test
    public void testAddDirectoryWithConnectionTest() throws DirectoryCurrentlySynchronisingException {
        Directory directory = createDirectory();
        doReturn(directory).when(crowdDirectoryService).addDirectory(any(Directory.class));

        DirectoryBean directoryBean = DirectoryBean.from(directory);
        directoryBean.setAppPassword("test");
        DirectoryBean directoryAdded = userDirectoryService.addDirectory(directoryBean, true);

        assertEquals(directoryAdded.getName(), directoryBean.getName());
    }

    @Test(expected = ValidationException.class)
    public void testAddDirectoryInvalidBean() throws DirectoryCurrentlySynchronisingException {
        Directory directory = createDirectory();
        DirectoryBean directoryBean = DirectoryBean.from(directory);
        directoryBean.setAppPassword("test");
        directoryBean.setClientName(null);

        userDirectoryService.addDirectory(directoryBean, false);
    }

    private Directory createDirectory() {
        Map<String, String> attributes = new HashMap<>();
        attributes.put(CROWD_SERVER_URL, "http://localhost");
        attributes.put(APPLICATION_PASSWORD, "test");
        attributes.put(APPLICATION_NAME, "confluence-client");
        attributes.put(CACHE_SYNCHRONISE_INTERVAL, "3600");
        attributes.put(ATTRIBUTE_KEY_USE_NESTED_GROUPS, "false");
        attributes.put(INCREMENTAL_SYNC_ENABLED, "true");
        attributes.put(SYNC_GROUP_MEMBERSHIP_AFTER_SUCCESSFUL_USER_AUTH_ENABLED, WHEN_AUTHENTICATION_CREATED_THE_USER.getValue());
        return ImmutableDirectory.builder("test", DirectoryType.CROWD, "test.class").setId(1L).setAttributes(attributes).build();
    }
}
