package com.atlassian.mail.server;

import com.atlassian.mail.MailProtocol;

public interface OtherTestPopMailServer extends DefaultTestMailServer, PopMailServer {

    MailProtocol PROTOCOL = MailProtocol.IMAP;
    String PORT = PROTOCOL.getDefaultPort();

}
