package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.AbstractDirectoryBean;
import de.aservo.confapi.commons.model.DirectoriesBean;
import de.aservo.confapi.commons.model.DirectoryCrowdBean;
import de.aservo.confapi.commons.service.api.DirectoriesService;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
public class AbstractDirectoriesResourceTest {

    @Mock
    private DirectoriesService directoriesService;

    private TestDirectoriesResourceImpl resource;

    @BeforeEach
    public void setup() {
        resource = new TestDirectoriesResourceImpl(directoriesService);
    }

    @Test
    public void testGetDirectories() {
        DirectoryCrowdBean initialDirectoryBean = DirectoryCrowdBean.EXAMPLE_1;
        DirectoriesBean directoriesBean = new DirectoriesBean(Collections.singleton(initialDirectoryBean));

        doReturn(directoriesBean).when(directoriesService).getDirectories();

        final Response response = resource.getDirectories();
        assertEquals(200, response.getStatus());
        final DirectoriesBean directoriesBeanResponse = (DirectoriesBean) response.getEntity();

        assertEquals(initialDirectoryBean, directoriesBeanResponse.getDirectories().iterator().next());
    }

    @Test
    public void testGetDirectory() {
        DirectoryCrowdBean directoryBean = DirectoryCrowdBean.EXAMPLE_1;

        doReturn(directoryBean).when(directoriesService).getDirectory(1L);

        final Response response = resource.getDirectory(1L);
        assertEquals(200, response.getStatus());
        final AbstractDirectoryBean directoryBeanResponse = (AbstractDirectoryBean) response.getEntity();

        assertEquals(directoryBean, directoryBeanResponse);
    }

    @Test
    public void testSetDirectories() {
        DirectoryCrowdBean directoryBean1 = DirectoryCrowdBean.EXAMPLE_1;
        DirectoryCrowdBean directoryBean2 = DirectoryCrowdBean.EXAMPLE_3;
        DirectoriesBean directoriesBean = new DirectoriesBean(Arrays.asList(directoryBean1, directoryBean2));

        doReturn(directoriesBean).when(directoriesService).setDirectories(directoriesBean, false);

        final Response response = resource.setDirectories(Boolean.FALSE, directoriesBean);
        assertEquals(200, response.getStatus());
        final DirectoriesBean directoriesBeanResponse = (DirectoriesBean) response.getEntity();

        assertEquals(directoriesBean.getDirectories().size(), directoriesBeanResponse.getDirectories().size());
    }

    @Test
    public void testSetDirectory() {
        DirectoryCrowdBean directoryBean = DirectoryCrowdBean.EXAMPLE_1;

        doReturn(directoryBean).when(directoriesService).setDirectory(1L, directoryBean, false);

        final Response response = resource.setDirectory(1L, Boolean.FALSE, directoryBean);
        assertEquals(200, response.getStatus());
        final AbstractDirectoryBean directoryBeanResponse = (AbstractDirectoryBean) response.getEntity();

        assertEquals(directoryBean, directoryBeanResponse);
    }

    @Test
    public void testAddDirectory() {
        DirectoryCrowdBean bean = DirectoryCrowdBean.EXAMPLE_1;

        doReturn(bean).when(directoriesService).addDirectory(bean, false);

        final Response response = resource.addDirectory(Boolean.FALSE, bean);
        assertEquals(200, response.getStatus());
        final AbstractDirectoryBean responseBean = (AbstractDirectoryBean) response.getEntity();

        assertEquals(bean.getName(), responseBean.getName());
    }

    @Test
    public void testDeleteDirectories() {
        resource.deleteDirectories(true);
        assertTrue(true, "Delete Successful");
    }

    @Test
    public void testDeleteDirectory() {
        resource.deleteDirectory(1L);
        assertTrue(true, "Delete Successful");
    }

}
