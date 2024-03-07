package com.ipi.quiditchmanager.customvalidator;

import com.ipi.quiditchmanager.customvalidator.PhoneNumber;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import java.util.regex.Pattern;

public class PhoneNumberValidator implements ConstraintValidator<PhoneNumber, String> {
    private static final String PHONE_NUMBER_REGEX = "^[0-9]{10}$";
    private static final String DEFAULT_MESSAGE = "Y'ouve not entered a valid phone number";

    @Override
    public void initialize(PhoneNumber phoneNumber) {
    }

    @Override
    public boolean isValid(String phoneField, ConstraintValidatorContext cxt) {
        if (phoneField == null || !Pattern.matches(PHONE_NUMBER_REGEX, phoneField)) {
            cxt.buildConstraintViolationWithTemplate(DEFAULT_MESSAGE)

                    .addConstraintViolation()
                    .disableDefaultConstraintViolation();
            return false;
        }

        return true;
    }

}