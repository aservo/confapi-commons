package de.aservo.atlassian.confapi.model;

import de.aservo.atlassian.confapi.constants.ConfAPI;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Bean for general settings in REST requests.
 */
@XmlRootElement(name = ConfAPI.SETTINGS)
public class SettingsBean {

    @XmlElement
    private final String baseurl;

    @XmlElement
    private final String title;

    /**
     * The default constructor is needed for JSON request deserialization.
     */
    public SettingsBean() {
        this.baseurl = null;
        this.title = null;
    }

    public SettingsBean(
            final String baseurl,
            final String title) {

        this.baseurl = baseurl;
        this.title = title;
    }

    public String getBaseurl() {
        return baseurl;
    }

    public String getTitle() {
        return title;
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
