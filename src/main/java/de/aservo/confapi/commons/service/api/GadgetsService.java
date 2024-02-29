package de.aservo.confapi.commons.service.api;

import de.aservo.confapi.commons.model.GadgetBean;
import de.aservo.confapi.commons.model.GadgetsBean;

import jakarta.validation.constraints.NotNull;

public interface GadgetsService {

    /**
     * Gets all gadgets.
     *
     * @return the gadgets
     */
    GadgetsBean getGadgets();

    /**
     * Gets a single gadget.
     *
     * @param id the gadget id to query
     * @return the gadget
     */
    GadgetBean getGadget(
            final long id);

    /**
     * Sets or updates the gadgets set.
     *
     * @param gadgetsBean the gadgets bean
     * @return the updated gadgets
     */
    GadgetsBean setGadgets(
            @NotNull final GadgetsBean gadgetsBean);

    /**
     * Updates a single gadgets.
     *
     * @param id         the gadget id to update
     * @param gadgetBean the gadgets bean
     * @return the updated gadgets
     */
    GadgetBean setGadget(
            final long id,
            @NotNull final GadgetBean gadgetBean);

    /**
     * Add one single gadget.
     *
     * @param gadgetBean the gadget bean to add
     * @return the added gadget
     */
    GadgetBean addGadget(
            @NotNull final GadgetBean gadgetBean);

    /**
     * Deletes all gadgets
     *
     * @param force must be set to 'true' in order to delete all entries
     */
    void deleteGadgets(
            boolean force);

    /**
     * Deletes a single gadget
     *
     * @param id the gadget id to delete
     */
    void deleteGadget(
            final long id);

}
