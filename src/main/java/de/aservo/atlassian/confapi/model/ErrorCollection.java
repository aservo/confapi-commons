package de.aservo.atlassian.confapi.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

@XmlRootElement
public class ErrorCollection {

    @XmlElement
    private final Collection<String> errorMessages = new ArrayList<>();

    public ErrorCollection addErrorMessage(String errorMessage) {
        if (errorMessage != null) {
            errorMessages.add(errorMessage);
        }
        return this;
    }

    public ErrorCollection addErrorMessages(Collection<String> messages) {
        if (messages != null) {
            messages.forEach(this::addErrorMessage);
        }
        return this;
    }

    public boolean hasAnyErrors() {
        return !errorMessages.isEmpty();
    }

    public Collection<String> getErrorMessages() {
        return errorMessages;
    }

    public static ErrorCollection of(String... messages) {
        return of(Arrays.asList(messages));
    }

    public static ErrorCollection of(Collection<String> messages) {
        return new ErrorCollection().addErrorMessages(messages);
    }

}
