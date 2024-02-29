package de.aservo.confapi.commons.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.Test;

import java.util.HashSet;
import java.util.Set;

public class BeanValidationUtilTest {

    @Test
    public void testThrowNoValidationException() {
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        BeanValidationUtil.processValidationResult(violations);
    }

    @Test(expected = ValidationException.class)
    public void testThrowValidationException() {
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        violations.add(ConstraintViolationImpl.forBeanValidation("", null, null, "",
                null, null, null, null, null, null, null));
        BeanValidationUtil.processValidationResult(violations);
    }
}
