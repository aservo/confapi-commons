package com.atlassian.mail.server;

import com.atlassian.mail.server.impl.PopMailServerImpl;

public class DefaultTestPopMailServerImpl extends PopMailServerImpl implements DefaultTestPopMailServer {

    public DefaultTestPopMailServerImpl() {
        super(
                null,
                NAME,
                DESCRIPTION,
                PROTOCOL,
                HOST,
                PORT,
                USERNAME,
                PASSWORD,
                TIMEOUT
        );
    }

}
