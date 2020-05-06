package de.aservo.atlassian.confapi.model;

import de.aservo.atlassian.confapi.constants.ConfAPI;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Collection;
import java.util.Collections;

/**
 * Bean for a gadget in REST requests.
 */
@Data
@AllArgsConstructor
@XmlRootElement(name = ConfAPI.GADGETS)
public class GadgetsBean {

    @XmlElement
    private Collection<GadgetBean> gadgets;

    // Example instances for documentation and tests

    public static final GadgetsBean EXAMPLE_1 = new GadgetsBean(Collections.singleton(GadgetBean.EXAMPLE_1));

}
