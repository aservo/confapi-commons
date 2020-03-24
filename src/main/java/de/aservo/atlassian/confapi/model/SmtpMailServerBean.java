package de.aservo.atlassian.confapi.model;

import com.atlassian.mail.server.SMTPMailServer;
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
 * Bean for SMTP mail server in REST requests.
 */
@XmlRootElement(name = ConfAPI.MAIL_SMTP)
public class SmtpMailServerBean {

    @XmlElement
    private final String name;

    @XmlElement
    private final String description;

    @XmlElement
    private final String adminContact;

    @XmlElement
    private final String from;

    @XmlElement
    private final String prefix;

    @XmlElement
    private final String protocol;

    @XmlElement
    private final String host;

    @XmlElement
    private final Integer port;

    @XmlElement
    private final boolean tls;

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
    public SmtpMailServerBean() {
        this.name = null;
        this.description = null;
        this.adminContact = null;
        this.from = null;
        this.prefix = null;
        this.protocol = null;
        this.host = null;
        this.port = null;
        this.tls = false;
        this.timeout = DEFAULT_TIMEOUT;
        this.username = null;
        this.password = null;
    }

    public SmtpMailServerBean(
            final String name,
            final String description,
            final String adminContact,
            final String from,
            final String prefix,
            final String protocol,
            final String host,
            final Integer port,
            final boolean tls,
            final long timeout,
            final String username) {

        this.name = name;
        this.description = StringUtils.isNoneBlank(description) ? description : null;
        this.adminContact = adminContact;
        this.from = from;
        this.prefix = prefix;
        this.protocol = protocol;
        this.host = host;
        this.port = port;
        this.tls = tls;
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

    public String getAdminContact() {
        return adminContact;
    }

    public String getFrom() {
        return from;
    }

    public String getPrefix() {
        return prefix;
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

    public boolean isTls() {
        return tls;
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

    public static SmtpMailServerBean from(
            final SMTPMailServer smtpMailServer) throws NoContentException {

        if (smtpMailServer == null) {
            throw new NoContentException("No SMTP mail server defined");
        }

        return new SmtpMailServerBean(
                smtpMailServer.getName(),
                smtpMailServer.getDescription(),
                null,
                smtpMailServer.getDefaultFrom(),
                smtpMailServer.getPrefix(),
                smtpMailServer.getMailProtocol().getProtocol(),
                smtpMailServer.getHostname(),
                StringUtils.isNotBlank(smtpMailServer.getPort()) ? Integer.parseInt(smtpMailServer.getPort()) : null,
                smtpMailServer.isTlsRequired(),
                smtpMailServer.getTimeout(),
                smtpMailServer.getUsername());
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
