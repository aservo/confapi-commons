package de.aservo.confapi.commons.util;

import jakarta.validation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Bean validation service.
 */
public class BeanValidationUtil {

    /**
     * Validates the given bean using Validation from hibernate reference.
     *
     * @param bean the bean
     */
    public static void validate(Object bean) {
        Validator validator;
        try (ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory()) {
            validator = validatorFactory.getValidator();
            processValidationResult(validator.validate(bean));
        }
    }

    /**
     * Processes validation violations by throwing a ValidationException including the violation texts
     *
     * @param violations validation violations
     */
    public static void processValidationResult(Set<ConstraintViolation<Object>> violations) {
        if (!violations.isEmpty()) {
            List<String> collect = violations.stream().map(v -> v.getPropertyPath() + ": " + v.getMessage()).collect(Collectors.toList());
            throw new ValidationException(String.join("\n", collect));
        }
    }

    private BeanValidationUtil() {
    }

}
