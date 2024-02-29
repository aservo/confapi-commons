package de.aservo.confapi.commons.model;

import de.aservo.confapi.commons.constants.ConfAPI;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.net.URI;

/**
 * Bean for user directory settings in REST requests.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = ConfAPI.DIRECTORY + '-' + ConfAPI.DIRECTORY_CROWD)
public class DirectoryCrowdBean extends AbstractDirectoryBean {

    @XmlElement
    private DirectoryCrowdServer server;

    @XmlElement
    private DirectoryCrowdPermissions permissions;

    @XmlElement
    private DirectoryCrowdAdvanced advanced;

    @Data
    @NoArgsConstructor
    public static class DirectoryCrowdServer {

        @XmlElement
        @NotNull
        private URI url;

        @XmlElement
        private DirectoryCrowdServerProxy proxy;

        @XmlElement
        @NotNull
        private String appUsername;

        @XmlElement
        @NotNull
        private String appPassword;

        @XmlElement
        private Long connectionTimeoutInMillis;

        @XmlElement
        private Integer maxConnections;

        @Data
        @NoArgsConstructor
        public static class DirectoryCrowdServerProxy {

            @XmlElement
            private String host;

            @XmlElement
            private Integer port;

            @XmlElement
            private String username;

            @XmlElement
            private String password;
        }
    }

    @Data
    @NoArgsConstructor
    public static class DirectoryCrowdPermissions {

        @XmlElement
        private Boolean readOnly;

        @XmlElement
        private Boolean fullAccess;
    }

    @Data
    @NoArgsConstructor
    public static class DirectoryCrowdAdvanced {

        @XmlElement
        private Boolean enableNestedGroups;

        @XmlElement
        private Boolean enableIncrementalSync;

        @XmlElement
        private String updateGroupMembershipMethod;

        @XmlElement
        private Integer updateSyncIntervalInMinutes;
    }

    // Example instances for documentation and tests

    public static final DirectoryCrowdBean EXAMPLE_1;

    static {
        EXAMPLE_1 = new DirectoryCrowdBean();
        EXAMPLE_1.setName("example");
        EXAMPLE_1.setServer(new DirectoryCrowdServer());
        EXAMPLE_1.getServer().setUrl(URI.create("https://crowd.example.com"));
        EXAMPLE_1.getServer().setAppPassword("p455w0rd");
    }

    public static final DirectoryCrowdBean EXAMPLE_1_WITH_PROXY;

    static {
        EXAMPLE_1_WITH_PROXY = new DirectoryCrowdBean();
        EXAMPLE_1_WITH_PROXY.setName("example");
        EXAMPLE_1_WITH_PROXY.setServer(new DirectoryCrowdServer());
        EXAMPLE_1_WITH_PROXY.getServer().setUrl(URI.create("https://crowd.example.com"));
        EXAMPLE_1_WITH_PROXY.getServer().setAppPassword("p433w0rd");
        EXAMPLE_1_WITH_PROXY.getServer().setProxy(new DirectoryCrowdServer.DirectoryCrowdServerProxy());
        EXAMPLE_1_WITH_PROXY.getServer().getProxy().setHost("proxy.example.com");
        EXAMPLE_1_WITH_PROXY.getServer().getProxy().setUsername("user");
        EXAMPLE_1_WITH_PROXY.getServer().getProxy().setPassword("pass");
    }

    public static final DirectoryCrowdBean EXAMPLE_2;

    static {
        EXAMPLE_2 = new DirectoryCrowdBean();
        EXAMPLE_2.setName("example2");
        EXAMPLE_2.setServer(new DirectoryCrowdServer());
        EXAMPLE_2.getServer().setUrl(URI.create("https://crowd2.example.com"));
        EXAMPLE_2.getServer().setAppPassword("0th3r");
    }

    public static final DirectoryCrowdBean EXAMPLE_3;

    static {
        EXAMPLE_3 = new DirectoryCrowdBean();
        EXAMPLE_3.setName("example other");
        EXAMPLE_3.setServer(new DirectoryCrowdServer());
        EXAMPLE_3.getServer().setUrl(URI.create("https://crowd3.example.com"));
        EXAMPLE_3.getServer().setAppPassword("p466w0rd");
    }

}
