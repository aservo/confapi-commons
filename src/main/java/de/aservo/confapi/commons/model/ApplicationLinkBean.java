package de.aservo.confapi.commons.model;

import de.aservo.confapi.commons.constants.ConfAPI;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;
import java.util.UUID;

/**
 * Bean for an application link in REST requests.
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = ConfAPI.APPLICATION_LINK)
public class ApplicationLinkBean {

    public enum ApplicationLinkType {
        BAMBOO,
        JIRA,
        BITBUCKET,
        CONFLUENCE,
        FISHEYE,
        CROWD
    }

    public enum ApplicationLinkStatus {
        AVAILABLE,
        UNAVAILABLE,
        CONFIGURATION_ERROR
    }

    @XmlElement
    private UUID uuid;

    @XmlElement
    @NotNull
    private String name;

    @XmlElement
    @NotNull
    private ApplicationLinkType type;

    @XmlElement
    @NotNull
    private URI displayUrl;

    @XmlElement
    @NotNull
    private URI rpcUrl;

    @XmlElement
    private Boolean primary;

    @XmlElement
    private ApplicationLinkStatus status;

    @XmlElement
    private String username;

    @XmlElement
    private String password;

    // Example instances for documentation and tests

    public static final ApplicationLinkBean EXAMPLE_1;

    static {
        EXAMPLE_1 = new ApplicationLinkBean();
        EXAMPLE_1.setUuid(UUID.randomUUID());
        EXAMPLE_1.setName("Example");
        EXAMPLE_1.setDisplayUrl(URI.create("http://example.com"));
        EXAMPLE_1.setRpcUrl(URI.create("http://rpc.example.com"));
        EXAMPLE_1.setPrimary(true);
        EXAMPLE_1.setType(ApplicationLinkType.JIRA);
        EXAMPLE_1.setUsername("username");
        EXAMPLE_1.setPassword("p455w0rd");
    }

}
