package atlassian.mail.server;

import com.atlassian.mail.MailProtocol;
import com.atlassian.mail.server.PopMailServer;

public interface OtherTestPopMailServer extends DefaultTestMailServer, PopMailServer {

    MailProtocol PROTOCOL = MailProtocol.IMAP;
    String PORT = PROTOCOL.getDefaultPort();

}
