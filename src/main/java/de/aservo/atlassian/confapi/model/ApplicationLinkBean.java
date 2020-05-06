package de.aservo.atlassian.confapi.model;

import com.atlassian.applinks.api.ApplicationLink;
import com.atlassian.applinks.api.ApplicationType;
import com.atlassian.applinks.api.application.bamboo.BambooApplicationType;
import com.atlassian.applinks.api.application.bitbucket.BitbucketApplicationType;
import com.atlassian.applinks.api.application.confluence.ConfluenceApplicationType;
import com.atlassian.applinks.api.application.crowd.CrowdApplicationType;
import com.atlassian.applinks.api.application.fecru.FishEyeCrucibleApplicationType;
import com.atlassian.applinks.api.application.jira.JiraApplicationType;
import com.atlassian.applinks.spi.link.ApplicationLinkDetails;
import de.aservo.atlassian.confapi.constants.ConfAPI;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.NotImplementedException;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.UUID;

/**
 * Bean for an application link in REST requests.
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = ConfAPI.APPLICATION_LINK)
public class ApplicationLinkBean {

    @XmlElement
    private String serverId;

    @XmlElement
    private String appType;

    @XmlElement
    @NotNull
    private ApplicationLinkTypes linkType;

    @XmlElement
    @NotNull
    @Size(min = 1)
    private String name;

    @XmlElement
    @NotNull
    @Size(min = 1)
    private String displayUrl;

    @XmlElement
    @NotNull
    @Size(min = 1)
    private String rpcUrl;

    @XmlElement
    private boolean primary;

    @XmlElement
    private String username;

    @XmlElement
    private String password;

    /**
     * Instantiates a new Application link bean.
     *
     * @param linkDetails the link details
     */
    public ApplicationLinkBean(ApplicationLink linkDetails) {
        serverId = linkDetails.getId().toString();
        appType = linkDetails.getType().toString();
        name = linkDetails.getName();
        displayUrl = linkDetails.getDisplayUrl().toString();
        rpcUrl = linkDetails.getRpcUrl().toString();
        primary = linkDetails.isPrimary();
        linkType = getLinktypeFromAppType(linkDetails.getType());
    }

    /**
     * Gets the linktype ApplicationLinkTypes enum value.
     *
     * @param type the ApplicationType
     * @return the linktype
     */
    private ApplicationLinkTypes getLinktypeFromAppType(ApplicationType type) {
        if (type instanceof BambooApplicationType) {
            return ApplicationLinkTypes.BAMBOO;
        } else if (type instanceof JiraApplicationType) {
            return ApplicationLinkTypes.JIRA;
        } else if (type instanceof BitbucketApplicationType) {
            return ApplicationLinkTypes.BITBUCKET;
        } else if (type instanceof ConfluenceApplicationType) {
            return ApplicationLinkTypes.CONFLUENCE;
        } else if (type instanceof FishEyeCrucibleApplicationType) {
            return ApplicationLinkTypes.FISHEYE;
        } else if (type instanceof CrowdApplicationType) {
            return ApplicationLinkTypes.CROWD;
        } else {
            throw new NotImplementedException("application type '" + type.getClass() + "' not implemented");
        }
    }

    /**
     * To application link details application link details.
     *
     * @return the application link details
     * @throws URISyntaxException the uri syntax exception
     */
    public ApplicationLinkDetails toApplicationLinkDetails() throws URISyntaxException {
        return ApplicationLinkDetails.builder().name(name).displayUrl(new URI(displayUrl)).rpcUrl(new URI(rpcUrl)).isPrimary(primary).build();
    }

    // Example instances for documentation and tests

    public static final ApplicationLinkBean EXAMPLE_1;

    static {
        EXAMPLE_1 = new ApplicationLinkBean();
        EXAMPLE_1.setName("Example");
        EXAMPLE_1.setDisplayUrl("http://example.com");
        EXAMPLE_1.setRpcUrl("http://rpc.example.com");
        EXAMPLE_1.setPrimary(true);
        EXAMPLE_1.setServerId(UUID.randomUUID().toString());
        EXAMPLE_1.setAppType("jira");
        EXAMPLE_1.setUsername("username");
        EXAMPLE_1.setPassword("p455w0rd");
    }

}
