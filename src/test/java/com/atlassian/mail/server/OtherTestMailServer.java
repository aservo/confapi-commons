package com.atlassian.mail.server;

import com.atlassian.mail.server.MailServer;

public interface OtherTestMailServer extends MailServer {

    String NAME = "OTHER Mail";
    String DESCRIPTION = NAME + " Description";
    String HOST = "other.aservo.com";
    long TIMEOUT = 10000L;
    String USERNAME = "otheruser";
    String PASSWORD = "otherpass";

}
