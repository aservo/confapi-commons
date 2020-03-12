package de.aservo.atlassian.confapi.service;

import com.atlassian.crowd.embedded.api.CrowdDirectoryService;
import com.atlassian.crowd.embedded.api.Directory;
import com.atlassian.crowd.exception.DirectoryCurrentlySynchronisingException;
import com.atlassian.plugin.spring.scanner.annotation.component.BambooComponent;
import com.atlassian.plugin.spring.scanner.annotation.component.BitbucketComponent;
import com.atlassian.plugin.spring.scanner.annotation.component.ConfluenceComponent;
import com.atlassian.plugin.spring.scanner.annotation.component.JiraComponent;
import com.atlassian.plugin.spring.scanner.annotation.export.ExportAsService;
import com.atlassian.plugin.spring.scanner.annotation.imports.ComponentImport;
import de.aservo.atlassian.confapi.model.UserDirectoryBean;
import de.aservo.atlassian.confapi.util.BeanValidationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static com.google.common.base.Preconditions.checkNotNull;

/**
 * The type User directory service.
 */
@ExportAsService
@BambooComponent
@BitbucketComponent
@ConfluenceComponent
@JiraComponent
public class UserDirectoryServiceImpl implements UserDirectoryService {

    private static final Logger log = LoggerFactory.getLogger(UserDirectoryServiceImpl.class);

    private final CrowdDirectoryService crowdDirectoryService;

    /**
     * Instantiates a new User directory service.
     *
     * @param crowdDirectoryService the crowd directory service
     */
    public UserDirectoryServiceImpl(@ComponentImport CrowdDirectoryService crowdDirectoryService) {
        this.crowdDirectoryService = checkNotNull(crowdDirectoryService);
    }

    /**
     * Gets directories.
     *
     * @return the directories
     */
    public List<UserDirectoryBean> getDirectories() {
        return crowdDirectoryService.findAllDirectories().stream().map(UserDirectoryBean::from).collect(Collectors.toList());
    }

    /**
     * Adds a new directory configurations. Any existing configurations with the same 'name' property are removed before adding the new configuration.
     *
     * @param directoryBean      the directory
     * @param testConnection whether to test connection
     * @return the configuration added
     * @throws DirectoryCurrentlySynchronisingException the directory currently synchronising exception
     */
    public UserDirectoryBean addDirectory(UserDirectoryBean directoryBean, boolean testConnection) throws DirectoryCurrentlySynchronisingException {
        //preps and validation
        BeanValidationUtil.validate(directoryBean);
        Directory directory = directoryBean.toDirectory();
        String directoryName = directoryBean.getName();
        if (testConnection) {
            log.debug("testing user directory connection for {}", directoryName);
            crowdDirectoryService.testConnection(directory);
        }

        //check if directory exists already and if yes, remove it
        Optional<Directory> presentDirectory = crowdDirectoryService.findAllDirectories().stream().filter(dir -> dir.getName().equals(directory.getName())).findFirst();
        if (presentDirectory.isPresent()) {
            Directory presentDir = presentDirectory.get();
            log.info("removing existing user directory configuration '{}' before adding new configuration '{}'", presentDir.getName(), directory.getName());
            crowdDirectoryService.removeDirectory(presentDir.getId());
        }

        //add new directory
        return UserDirectoryBean.from(crowdDirectoryService.addDirectory(directory));
    }
}
