package it.de.aservo.confapi.commons.rest;

import jakarta.ws.rs.core.Application;
import java.util.Collections;
import java.util.Set;

public class ClientApplication extends Application {

    private Set<Object> singletons = Collections.emptySet();

    @Override
    public Set<Object> getSingletons() {
        return singletons;
    }

    public void setSingletons(final Set<Object> singletons) {
        this.singletons = singletons;
    }
}