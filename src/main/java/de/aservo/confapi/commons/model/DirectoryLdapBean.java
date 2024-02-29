package de.aservo.confapi.commons.model;

import de.aservo.confapi.commons.constants.ConfAPI;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bean for user directory settings in REST requests.
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@XmlRootElement(name = ConfAPI.DIRECTORY + '-' + ConfAPI.DIRECTORY_LDAP)
public class DirectoryLdapBean extends AbstractDirectoryBean {

    @XmlElement
    private DirectoryLdapServer server;

    @XmlElement
    private DirectoryLdapSchema schema;

    @XmlElement
    private DirectoryLdapPermissions permissions;

    @Data
    @NoArgsConstructor
    public static class DirectoryLdapServer {

        @XmlElement
        @NotNull
        private String host;

        @XmlElement
        private Integer port;

        @XmlElement
        private Boolean useSsl;

        @XmlElement
        private String username;

        @XmlElement
        private String password;
    }

    @Data
    @NoArgsConstructor
    public static class DirectoryLdapSchema {

        @XmlElement
        private String baseDn;

        @XmlElement
        private String userDn;

        @XmlElement
        private String groupDn;
    }

    @Data
    @NoArgsConstructor
    public static class DirectoryLdapPermissions {

        @XmlElement
        private Boolean readOnly;

        @XmlElement
        private Boolean readOnlyForLocalGroups;

        @XmlElement
        private Boolean fullAccess;
    }
}
