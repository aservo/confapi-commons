package com.atlassian.mail.server;

import com.atlassian.mail.server.impl.SMTPMailServerImpl;

public class OtherTestSmtpMailServerImpl extends SMTPMailServerImpl implements OtherTestSmtpMailServer {

    public OtherTestSmtpMailServerImpl() {
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
