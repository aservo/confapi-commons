package de.aservo.atlassian.confapi.util;

import org.apache.commons.lang3.StringUtils;

import javax.validation.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * The type Bean validation service.
 */
public class BeanValidationUtil {

    private BeanValidationUtil() {}

    /**
     * Validates the given bean using javax.validation impl from hibernate reference.
     *
     * @param bean the bean
     * @throws ValidationException the validation exception
     */
    public static void validate(Object bean) {
        ValidatorFactory validatorFactory = Validation.buildDefaultValidatorFactory();
        Validator validator = validatorFactory.getValidator();
        Set<ConstraintViolation<Object>> violations = validator.validate(bean);
        if (!violations.isEmpty()) {
            List<String> collect = violations.stream().map(v -> v.getPropertyPath() + ": " + v.getMessage()).collect(Collectors.toList());
            throw new ValidationException(StringUtils.join(collect, "\n"));
        }
    }
}
