package atlassian.mail.server;

import com.atlassian.mail.MailProtocol;
import com.atlassian.mail.server.SMTPMailServer;

public interface OtherTestSmtpMailServer extends DefaultTestMailServer, SMTPMailServer {

    String FROM = "from.other@aservo.com";
    String PREFIX = "[OTHER]";
    boolean TLS_REQUIRED = true;
    MailProtocol PROTOCOL = MailProtocol.SECURE_SMTP;
    String PORT = PROTOCOL.getDefaultPort();

}
