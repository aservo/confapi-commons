package com.atlassian.mail.server;

import com.atlassian.mail.server.impl.SMTPMailServerImpl;

public class DefaultTestSmtpMailServerImpl extends SMTPMailServerImpl implements DefaultTestSmtpMailServer {

    public DefaultTestSmtpMailServerImpl() {
        super(
                null,
                NAME,
                DESCRIPTION,
                FROM,
                PREFIX,
                false,
                PROTOCOL,
                HOST,
                PORT,
                TLS_REQUIRED,
                USERNAME,
                PASSWORD,
                TIMEOUT
        );
    }

}
