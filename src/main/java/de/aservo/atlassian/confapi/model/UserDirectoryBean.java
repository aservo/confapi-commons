package de.aservo.atlassian.confapi.model;

import com.atlassian.crowd.embedded.api.Directory;
import com.atlassian.crowd.embedded.api.DirectoryType;
import com.atlassian.crowd.model.directory.ImmutableDirectory;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.HashMap;
import java.util.Map;

import static com.atlassian.crowd.directory.RemoteCrowdDirectory.*;
import static com.atlassian.crowd.directory.SynchronisableDirectoryProperties.*;
import static com.atlassian.crowd.directory.SynchronisableDirectoryProperties.SyncGroupMembershipsAfterAuth.WHEN_AUTHENTICATION_CREATED_THE_USER;
import static com.atlassian.crowd.model.directory.DirectoryImpl.ATTRIBUTE_KEY_USE_NESTED_GROUPS;

/**
 * Bean for user directory settings in REST requests.
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = "userDirectory")
public class UserDirectoryBean {

    @XmlElement
    private boolean active;

    @XmlElement
    @NotNull
    @Size(min = 1)
    private String name;

    @XmlElement
    @NotNull
    @Size(min = 1)
    private String clientName;

    @XmlElement
    @NotNull
    private DirectoryType type;

    @XmlElement
    private String description;

    @XmlElement
    @NotNull
    @Size(min = 1)
    private String crowdUrl;

    @XmlElement
    @NotNull
    @Size(min = 1)
    private String appPassword;

    @XmlElement
    @NotNull
    @Size(min = 1)
    private String implClass;

    @XmlElement
    private String proxyHost;

    @XmlElement
    private String proxyPort;

    @XmlElement
    private String proxyUsername;

    @XmlElement
    private String proxyPassword;

    /**
     * Build directory directory.
     *
     * @return the directory
     */
    public Directory toDirectory() {
        Map<String, String> attributes = new HashMap<>();
        attributes.put(CROWD_SERVER_URL, crowdUrl);
        attributes.put(APPLICATION_PASSWORD, appPassword);
        attributes.put(APPLICATION_NAME, clientName);
        attributes.put(CROWD_HTTP_PROXY_HOST, proxyHost);
        attributes.put(CROWD_HTTP_PROXY_PORT, proxyPort);
        attributes.put(CROWD_HTTP_PROXY_USERNAME, proxyUsername);
        attributes.put(CROWD_HTTP_PROXY_PASSWORD, proxyPassword);
        attributes.put(CACHE_SYNCHRONISE_INTERVAL, "3600");
        attributes.put(ATTRIBUTE_KEY_USE_NESTED_GROUPS, "false");
        attributes.put(INCREMENTAL_SYNC_ENABLED, "true");
        attributes.put(SYNC_GROUP_MEMBERSHIP_AFTER_SUCCESSFUL_USER_AUTH_ENABLED, WHEN_AUTHENTICATION_CREATED_THE_USER.getValue());

        return ImmutableDirectory.builder(name, type, implClass).setActive(active).setAttributes(attributes).build();
    }

    /**
     * Build user directory bean user directory bean.
     *
     * @param directory the directory
     * @return the user directory bean
     */
    public static UserDirectoryBean from(Directory directory) {
        Map<String, String> attributes = directory.getAttributes();
        UserDirectoryBean directoryBean = new UserDirectoryBean();
        directoryBean.setName(directory.getName());
        directoryBean.setActive(directory.isActive());
        directoryBean.setType(directory.getType());
        directoryBean.setDescription(directory.getDescription());
        directoryBean.setCrowdUrl(attributes.get(CROWD_SERVER_URL));
        directoryBean.setClientName(attributes.get(APPLICATION_NAME));
        directoryBean.setProxyHost(attributes.get(CROWD_HTTP_PROXY_HOST));
        directoryBean.setProxyPort(attributes.get(CROWD_HTTP_PROXY_PORT));
        directoryBean.setProxyUsername(attributes.get(CROWD_HTTP_PROXY_USERNAME));
        directoryBean.setImplClass(directory.getImplementationClass());
        return directoryBean;
    }
}
