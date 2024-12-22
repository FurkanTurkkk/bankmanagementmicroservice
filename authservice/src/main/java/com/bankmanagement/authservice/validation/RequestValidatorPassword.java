package com.bankmanagement.authservice.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class RequestValidatorPassword implements ConstraintValidator<ValidPassword,String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value==null || value.isEmpty()){
            return false;
        }
        if (value.matches("\\s")){
            return false;
        }
        return true;
    }
}
