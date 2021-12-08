package de.aservo.confapi.commons.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsExclude;
import org.apache.commons.lang3.builder.HashCodeExclude;
import org.codehaus.jackson.annotate.JsonIgnore;

import javax.xml.bind.annotation.XmlElement;

@Data
@NoArgsConstructor
public abstract class AbstractMailServerProtocolBean {

    public static final Long DEFAULT_TIMEOUT = 10000L; //TODO still needed?

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
    @JsonIgnore
    //prevent "Conflicting setter definitions for property \"port\", see https://stackoverflow.com/questions/6346018/deserializing-json-into-object-with-overloaded-methods-using-jackson
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

        if (port != null) {
            this.port = Integer.parseInt(port);
        } else {
            this.port = null; // Explicitly allow setting null
        }
    }

    /**
     * Make sure always to set protocol in lowercase format.
     *
     * @param protocol the protocol
     */
    public void setProtocol(
            final String protocol) {

        if (protocol != null) {
            this.protocol = protocol.toLowerCase();
        } else {
            this.protocol = null; // Explicitly allow setting null
        }
    }
}
