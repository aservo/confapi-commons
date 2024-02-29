package de.aservo.confapi.commons.junit;

import static de.aservo.confapi.commons.util.StringUtil.baseName;
import static org.junit.jupiter.api.Assertions.assertTrue;

public abstract class AbstractTest {

    private static final String TEST_CLASS_SUFFIX = "Test";

    private Class<?> baseClass = null;

    public Class<?> getBaseClass() {
        if (baseClass == null) {
            baseClass = findBaseClass();
        }

        return baseClass;
    }

    private Class<?> findBaseClass() {
        final String testClassName = getClass().getCanonicalName();
        assertTrue(testClassName.endsWith(TEST_CLASS_SUFFIX), "The test class should end with suffix Test");

        final Class<?> baseClass;
        final String baseClassName = baseName(testClassName, TEST_CLASS_SUFFIX);

        try {
            baseClass = Class.forName(baseClassName);
        } catch (ClassNotFoundException ignored) {
            throw new AssertionError("The base class of this test should be named " + baseClassName);
        }

        return baseClass;
    }

}
