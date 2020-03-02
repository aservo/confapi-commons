package com.atlassian.mail.server;

import com.atlassian.mail.MailConstants;
import com.atlassian.mail.MailProtocol;

public interface DefaultTestPopMailServer extends DefaultTestMailServer, PopMailServer {

    MailProtocol PROTOCOL = MailConstants.DEFAULT_POP_PROTOCOL;
    String PORT = PROTOCOL.getDefaultPort();

}
