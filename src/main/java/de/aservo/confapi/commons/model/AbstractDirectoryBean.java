package de.aservo.confapi.commons.model;

import de.aservo.confapi.commons.constants.ConfAPI;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.codehaus.jackson.annotate.JsonSubTypes;
import org.codehaus.jackson.annotate.JsonTypeInfo;

import jakarta.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Date;

/**
 * Bean for user directory settings in REST requests.
 */
@Data
@NoArgsConstructor
@XmlRootElement
@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type"
)
// Note: New subtypes must also be registered in DirectoriesBean.java to be considered in the REST API documentation
@JsonSubTypes({
        @JsonSubTypes.Type(value = DirectoryCrowdBean.class, name = ConfAPI.DIRECTORY_CROWD),
        @JsonSubTypes.Type(value = DirectoryDelegatingBean.class, name = ConfAPI.DIRECTORY_DELEGATING),
        @JsonSubTypes.Type(value = DirectoryGenericBean.class, name = ConfAPI.DIRECTORY_GENERIC),
        @JsonSubTypes.Type(value = DirectoryInternalBean.class, name = ConfAPI.DIRECTORY_INTERNAL),
        @JsonSubTypes.Type(value = DirectoryLdapBean.class, name = ConfAPI.DIRECTORY_LDAP),
})
public abstract class AbstractDirectoryBean {

    @XmlElement
    private Long id;

    @XmlElement
    @NotNull
    private String name;

    @XmlElement
    private String description;

    @XmlElement
    private Boolean active;

    @XmlElement
    private Date createdDate;

    @XmlElement
    private Date updatedDate;

}
