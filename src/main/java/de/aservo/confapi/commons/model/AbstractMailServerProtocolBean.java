package de.aservo.confapi.commons.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
public abstract class AbstractMailServerProtocolBean {

    public static final Long DEFAULT_TIMEOUT = 10000L;

    @XmlElement
    private String name;

    @XmlElement
    private String description;

    @XmlElement
    private String host;

    @XmlElement
    private Integer port;

    @XmlElement
    private String protocol;

    @XmlElement
    private Long timeout;

    @XmlElement
    private String username;

    @XmlElement
    @EqualsExclude
    @HashCodeExclude
    private String password;

    /**
     * Make sure port can be set from an int and from a String value.
     *
     * @param port the port
     */
    @JsonIgnore     //prevent "Conflicting setter definitions for property \"port\", see https://stackoverflow.com/questions/6346018/deserializing-json-into-object-with-overloaded-methods-using-jackson
    public void setPort(
            final int port) {

        this.port = port;
    }

    /**
     * Make sure port can be set from an int and from a String value.
     *
     * @param port the port
     */
    public void setPort(
            final String port) {

        if (StringUtils.isNotBlank(port)) {
            this.port = Integer.parseInt(port);
        }
    }

    /**
     * Make sure always to set protocol in lowercase format.
     *
     * @param protocol the protocol
     */
     public void setProtocol(
            final String protocol) {

        if (StringUtils.isNotBlank(protocol)) {
            this.protocol = protocol.toLowerCase();
        }
    }

    /**
     * Return set timeout or default timeout of 10000L if null.
     *
     * @return timeout
     */
    public Long getTimeout() {
        if (timeout == null) {
            return DEFAULT_TIMEOUT;
        }

        return timeout;
    }

}
