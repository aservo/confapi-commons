package de.aservo.confapi.commons.rest;

import de.aservo.confapi.commons.model.LicenseBean;
import de.aservo.confapi.commons.model.LicensesBean;
import de.aservo.confapi.commons.rest.api.LicensesResource;
import de.aservo.confapi.commons.service.api.LicensesService;

import jakarta.ws.rs.core.Response;

public abstract class AbstractLicensesResourceImpl implements LicensesResource {

    private final LicensesService licensesService;

    public AbstractLicensesResourceImpl(final LicensesService licensesService) {
        this.licensesService = licensesService;
    }

    @Override
    public Response getLicenses() {
        final LicensesBean licensesBean = licensesService.getLicenses();
        return Response.ok(licensesBean).build();
    }

    @Override
    public Response addLicense(
            final LicenseBean licenseBean) {
        LicenseBean updatedLicenseBean = licensesService.addLicense(licenseBean);
        return Response.ok(updatedLicenseBean).build();
    }
}
