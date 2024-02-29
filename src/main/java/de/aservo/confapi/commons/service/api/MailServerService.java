package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.MailServerPopBean;
import de.aservo.confapi.commons.model.MailServerSmtpBean;

import jakarta.validation.constraints.NotNull;

public interface MailServerService {

    /**
     * Get the smtp mailserver settings.
     *
     * @return the smtp mailserver settings
     */
    MailServerSmtpBean getMailServerSmtp();

    /**
     * Sets the smtp mailserver settings.
     *
     * @param smtpBean the smtp mailserver settings to set
     * @return the smtp mailserver settings
     */
    MailServerSmtpBean setMailServerSmtp(
            @NotNull final MailServerSmtpBean smtpBean);

    /**
     * Get the pop mailserver settings.
     *
     * @return the pop mailserver settings
     */
    MailServerPopBean getMailServerPop();

    /**
     * Sets the pop mailserver settings.
     *
     * @param popBean the pop mailserver settings to set
     * @return the pop mailserver settings
     */
    MailServerPopBean setMailServerPop(
            @NotNull final MailServerPopBean popBean);
}
