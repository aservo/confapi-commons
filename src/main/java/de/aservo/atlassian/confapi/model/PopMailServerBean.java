package de.aservo.atlassian.confapi.model;

import com.atlassian.mail.server.PopMailServer;
import de.aservo.atlassian.confapi.constants.ConfAPI;
import de.aservo.atlassian.confapi.exception.NoContentException;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import org.apache.commons.lang3.builder.HashCodeExclude;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import static com.atlassian.mail.MailConstants.DEFAULT_TIMEOUT;

/**
 * Bean for POP mail server in REST requests.
 */
@XmlRootElement(name = ConfAPI.MAIL_POP)
public class PopMailServerBean {

    @XmlElement
    private final String name;

    @XmlElement
    private final String description;

    @XmlElement
    private final String protocol;

    @XmlElement
    private final String host;

    @XmlElement
    private final Integer port;

    @XmlElement
    private final long timeout;

    @XmlElement
    private final String username;

    @XmlElement
    @EqualsExclude
    @HashCodeExclude
    private final String password;

    /**
     * The default constructor is needed for JSON request deserialization.
     */
    public PopMailServerBean() {
        this.name = null;
        this.description = null;
        this.protocol = null;
        this.host = null;
        this.port = null;
        this.timeout = DEFAULT_TIMEOUT;
        this.username = null;
        this.password = null;
    }

    private PopMailServerBean(
            final String name,
            final String description,
            final String protocol,
            final String host,
            final Integer port,
            final long timeout,
            final String username) {

        this.name = name;
        this.description = StringUtils.isNoneBlank(description) ? description : null;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.timeout = timeout;
        this.username = username;
        this.password = null;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public String getProtocol() {
        return protocol != null ? protocol.toLowerCase() : null;
    }

    public String getHost() {
        return host;
    }

    public Integer getPort() {
        return port;
    }

    public long getTimeout() {
        return timeout;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public static PopMailServerBean from(
            final PopMailServer popMailServer) throws NoContentException {

        if (popMailServer == null) {
            throw new NoContentException("No POP mail server defined");
        }

        return new PopMailServerBean(
                popMailServer.getName(),
                popMailServer.getDescription(),
                popMailServer.getMailProtocol().getProtocol(),
                popMailServer.getHostname(),
                StringUtils.isNotBlank(popMailServer.getPort()) ? Integer.parseInt(popMailServer.getPort()) : null,
                popMailServer.getTimeout(),
                popMailServer.getUsername());
    }

    @Override
    public int hashCode() {
        return HashCodeBuilder.reflectionHashCode(this);
    }

    @Override
    @SuppressWarnings("EqualsWhichDoesntCheckParameterClass")
    public boolean equals(Object o) {
        return EqualsBuilder.reflectionEquals(this, o);
    }

}
