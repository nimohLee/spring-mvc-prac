package controller;

import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

public class CHangePwdCommandValidator implements Validator {

    @Override
    public boolean supports(Class<?> aClass) {
        return ChangePwdCommand.class.isAssignableFrom(aClass);
    }

    @Override
    public void validate(Object target, Errors errors) {
        ValidationUtils.rejectIfEmptyOrWhitespace(
                errors, "currentPassword", "required"
        );
        ValidationUtils.rejectIfEmpty(errors, "newPassword", "required");
    }
}
