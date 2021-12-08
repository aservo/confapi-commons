package de.aservo.confapi.commons.model;

import de.aservo.confapi.commons.constants.ConfAPI;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;

/**
 * Bean for a licence info in REST requests.
 */
@Data
@NoArgsConstructor
@XmlRootElement(name = ConfAPI.LICENSE)
public class LicenseBean {

    @XmlElement
    private Collection<String> products;

    @XmlElement
    private String type;

    @XmlElement
    private String organization;

    @XmlElement
    private String description;

    @XmlElement
    private Date expiryDate;

    @XmlElement
    private Integer maxUsers;

    @XmlElement
    private String key;

    // Example instances for documentation and tests

    public static final LicenseBean EXAMPLE_1;
    public static final LicenseBean EXAMPLE_2_DEVELOPER_LICENSE;

    static {
        EXAMPLE_1 = new LicenseBean();
        EXAMPLE_1.setKey("ABC...");
        EXAMPLE_1.setDescription("Example License");
        EXAMPLE_1.setOrganization("Example Organization");
        EXAMPLE_1.setProducts(Collections.singleton("example-product"));
        EXAMPLE_1.setMaxUsers(10);
        EXAMPLE_1.setExpiryDate(new Date());

        // use "3 hour expiration for all Atlassian host products*"
        // from https://developer.atlassian.com/platform/marketplace/timebomb-licenses-for-testing-server-apps/
        EXAMPLE_2_DEVELOPER_LICENSE = new LicenseBean();
        EXAMPLE_2_DEVELOPER_LICENSE.setKey(
                "AAACLg0ODAoPeNqNVEtv4jAQvudXRNpbpUSEx6FIOQBxW3ZZiCB0V1WllXEG8DbYke3A8u/XdUgVQ\n" +
                "yg9ZvLN+HuM/e1BUHdGlNvuuEHQ73X73Y4bR4nbbgU9ZwFiD2IchcPH+8T7vXzuej9eXp68YSv45\n" +
                "UwoASYhOeYwxTsIE7RIxtNHhwh+SP3a33D0XnntuxHsIeM5CIdwtvYxUXQPoRIF6KaC0FUGVlEB3\n" +
                "v0hOAOWYiH9abFbgZith3i34nwOO65gsAGmZBhUbNC/nIpjhBWEcefJWelzqIDPWz/OtjmXRYv2X\n" +
                "yqwnwueFkT57x8e4cLmbCD1QnX0UoKQoRc4EUgiaK4oZ2ECUrlZeay75sLNs2JDmZtWR8oPCfWZG\n" +
                "wHAtjzXgIo0SqmZiKYJmsfz8QI5aI+zApuq6fqJKVPAMCPnNpk4LPW6kBWgkZb+kQAzzzS2g6Dnt\n" +
                "e69Tqvsr4SOskIqEFOeggz1v4zrHbr0yLJR8rU64FpQpVtBy1mZxM4CnHC9Faf8tKMnTF1AiXORF\n" +
                "ixyQaWto3RZ+ncWLXtMg6EnKZZRpmQNb2R8tnJXFulCfXmXLry7TrHBWn2HNVyH8WYxj9AzmsxiN\n" +
                "L/R88Xg6rA1lVs4QpO5titxhplJcCY2mFFZLutAZVhKipm15/VhJx36YVqyN8YP7IaGC1+lwnJ7Q\n" +
                "5pJpNmxk5hP3qovutY8Pi4E2WIJ59esnr1p+T6eD67teBVCHf+ga+ho4/4D9YItZDAsAhQ5qQ6pA\n" +
                "SJ+SA7YG9zthbLxRoBBEwIURQr5Zy1B8PonepyLz3UhL7kMVEs=X02q6");
        EXAMPLE_2_DEVELOPER_LICENSE.setDescription("Test license for plugin developers");
        EXAMPLE_2_DEVELOPER_LICENSE.setType("TESTING");
        EXAMPLE_2_DEVELOPER_LICENSE.setOrganization("Atlassian");
        EXAMPLE_2_DEVELOPER_LICENSE.setProducts(Arrays.asList(
                "Confluence",
                "Jira",
                "Bitbucket",
                "Crowd",
                "Bamboo",
                "Fisheye"));
        EXAMPLE_2_DEVELOPER_LICENSE.setMaxUsers(1);
        EXAMPLE_2_DEVELOPER_LICENSE.setExpiryDate(new Date());
    }

}
