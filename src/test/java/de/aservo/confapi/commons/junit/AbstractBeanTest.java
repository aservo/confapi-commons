package de.aservo.confapi.commons.junit;

import org.junit.jupiter.api.Test;

import javax.xml.bind.annotation.XmlRootElement;

import static org.junit.jupiter.api.Assertions.*;

public abstract class AbstractBeanTest extends AbstractTest {

    private static final String CLASS_SUFFIX = "Bean";

    @Test
    public void beanClassNameShouldEndWithSuffixBean() {
        final String beanClassName = getBaseClass().getSimpleName();
        assertTrue(beanClassName.endsWith(CLASS_SUFFIX), "The model class name should end with suffix " + CLASS_SUFFIX);
    }

    @Test
    public void beanClassNameAndXmlRootElementShouldMatch() {
        final String beanClassName = getBaseClass().getSimpleName();
        final String beanClassBaseName = beanClassName.substring(0, beanClassName.length() - CLASS_SUFFIX.length());
        final XmlRootElement xmlRootElement = getBaseClass().getAnnotation(XmlRootElement.class);
        assertNotNull(xmlRootElement);
        assertEquals(camelCaseToSnakeCase(beanClassBaseName), xmlRootElement.name(),
                "The model class camel-case base name and the xml root element kebab-case base name should match");
    }

    private static String camelCaseToSnakeCase(
            final String camelCase) {

        if (camelCase == null || camelCase.isEmpty()) {
            return camelCase;
        }

        final StringBuilder snakeCase = new StringBuilder();
        snakeCase.append(Character.toLowerCase(camelCase.charAt(0)));

        for (int i = 1; i < camelCase.length(); i++) {
            char charAt = camelCase.charAt(i);
            if (Character.isUpperCase(charAt)) {
                snakeCase.append('-').append(Character.toLowerCase(charAt));
            } else {
                snakeCase.append(charAt);
            }
        }

        return snakeCase.toString();
    }

}
