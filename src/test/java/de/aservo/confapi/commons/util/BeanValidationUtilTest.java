package de.aservo.confapi.commons.util;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ValidationException;
import org.hibernate.validator.internal.engine.ConstraintViolationImpl;
import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertThrows;

public class BeanValidationUtilTest {

    @Test
    public void testThrowNoValidationException() {
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        BeanValidationUtil.processValidationResult(violations);
    }

    @Test
    public void testThrowValidationException() {
        Set<ConstraintViolation<Object>> violations = new HashSet<>();
        violations.add(ConstraintViolationImpl.forBeanValidation("", null, null, "",
                null, null, null, null, null, null, null));

        assertThrows(ValidationException.class, () -> {
            BeanValidationUtil.processValidationResult(violations);
        });
    }
}
