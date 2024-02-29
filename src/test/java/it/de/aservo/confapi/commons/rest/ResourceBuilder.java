package it.de.aservo.confapi.commons.rest;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import org.apache.wink.client.ClientConfig;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.apache.wink.client.handlers.BasicAuthSecurityHandler;

import javax.ws.rs.core.MediaType;
import java.util.Collections;

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
     * Creates a new REST client for test purposes. Note that the client must be recreated like this for auth tests.
     * (Atlassian uses cookies for authentication which may survive otherwise and lead to false test results regarding auth)
     *
     * @return the built resource
     */
    public Resource build() {

        //create new client app with Jackson binding
        final ClientApplication clientApplication = new ClientApplication();
        clientApplication.setSingletons(Collections.singleton(new JacksonJsonProvider()));
        final ClientConfig config = new ClientConfig().applications(clientApplication);

        //setup http basic auth
        final BasicAuthSecurityHandler basicAuthHandler = new BasicAuthSecurityHandler();
        basicAuthHandler.setUserName(username);
        basicAuthHandler.setPassword(password);
        config.handlers(basicAuthHandler);
        RestClient restClient = new RestClient(config);

        //configure resource
        String resourceUrl = baseUrl + REST_PATH + resourceName;
        Resource resource = restClient.resource(resourceUrl);
        resource.accept(acceptMediaType);
        resource.contentType(contentMediaType);
        return resource;
    }
}
