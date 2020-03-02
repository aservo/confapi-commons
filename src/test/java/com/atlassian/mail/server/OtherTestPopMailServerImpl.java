package com.atlassian.mail.server;

import com.atlassian.mail.server.impl.PopMailServerImpl;

public class OtherTestPopMailServerImpl extends PopMailServerImpl implements OtherTestPopMailServer {

    public OtherTestPopMailServerImpl() {
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
