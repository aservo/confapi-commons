package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.LicenseBean;
import de.aservo.confapi.commons.model.LicensesBean;

import jakarta.validation.constraints.NotNull;

public interface LicensesService {

    /**
     * Gets all licenses.
     *
     * @return the licenses
     */
    LicensesBean getLicenses();

    /**
     * Add a single license
     *
     * @param licenseBean the single license to set
     * @return the added license
     */
    LicenseBean addLicense(
            @NotNull final LicenseBean licenseBean);

}
