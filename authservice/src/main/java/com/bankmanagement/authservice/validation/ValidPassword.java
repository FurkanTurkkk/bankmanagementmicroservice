package com.bankmanagement.authservice.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = RequestValidatorPassword.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ValidPassword {
    String message() default
            "Geçersiz şifre formatı: Şifre boşluk karakterleri içeremez.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
