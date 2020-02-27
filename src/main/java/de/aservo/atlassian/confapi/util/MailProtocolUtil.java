package de.aservo.atlassian.confapi.util;

import com.atlassian.mail.MailProtocol;

import java.util.Arrays;

import static org.apache.commons.lang3.StringUtils.isBlank;

public class MailProtocolUtil {

    private MailProtocolUtil() {}

    public static MailProtocol find(
            final String protocol,
            final MailProtocol defaultMailProtocol) {

        if (isBlank(protocol)) {
            return defaultMailProtocol;
        }

        return Arrays.stream(MailProtocol.values())
                .filter(p -> p.getProtocol().equals(protocol))
                .findAny().orElse(defaultMailProtocol);
    }

}
