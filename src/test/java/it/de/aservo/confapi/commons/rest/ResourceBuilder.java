package it.de.aservo.confapi.commons.rest;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Invocation;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;

public class ResourceBuilder {

    private static final String REST_PATH = "/rest/confapi/1/";
    private final String baseUrl = System.getProperty("baseurl");

    private String username = "admin";
    private String password = "admin";
    private String acceptMediaType = MediaType.APPLICATION_JSON;
    private String contentMediaType = MediaType.APPLICATION_JSON;
    private final String resourceName;

    private ResourceBuilder(final String resourceName) {
        this.resourceName = resourceName;
    }

    public static ResourceBuilder builder(final String resourceName) {
        return new ResourceBuilder(resourceName);
    }

    public ResourceBuilder username(final String username) {
        this.username = username;
        return this;
    }

    public ResourceBuilder password(final String password) {
        this.password = password;
        return this;
    }

    public ResourceBuilder acceptMediaType(final String acceptMediaType) {
        this.acceptMediaType = acceptMediaType;
        return this;
    }

    public ResourceBuilder contentMediaType(final String contentMediaType) {
        this.contentMediaType = contentMediaType;
        return this;
    }

    /**
     * Creates a new REST client for test purposes.
     *
     * @return the built Invocation.Builder which can be used to make requests
     */
    public Invocation.Builder build() {
        // Create a new client with Jackson provider for JSON processing
        WebTarget webTarget;
        try (Client client = ClientBuilder.newClient().register(new JacksonJsonProvider())) {

            // Configure HTTP basic authentication
            client.register(new BasicAuthenticator(username, password));

            // Configure the target resource URL
            String resourceUrl = baseUrl + REST_PATH + resourceName;
            webTarget = client.target(resourceUrl);
        }

        // Return the Invocation.Builder to configure and execute the request
        return webTarget.request(acceptMediaType).accept(contentMediaType);
    }

    /**
     * BasicAuthenticator class to configure HTTP Basic Authentication.
     */
    private static class BasicAuthenticator implements jakarta.ws.rs.client.ClientRequestFilter {
        private final String username;
        private final String password;

        public BasicAuthenticator(String username, String password) {
            this.username = username;
            this.password = password;
        }

        @Override
        public void filter(jakarta.ws.rs.client.ClientRequestContext requestContext) {
            String token = username + ":" + password;
            String base64Token = java.util.Base64.getEncoder().encodeToString(token.getBytes(java.nio.charset.StandardCharsets.UTF_8));
            requestContext.getHeaders().add("Authorization", "Basic " + base64Token);
        }
    }
}
