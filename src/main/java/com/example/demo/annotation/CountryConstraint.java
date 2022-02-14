package com.example.demo.annotation;


import com.example.demo.validator.FranceValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *Annotation for validator the Country of the user
 */
@Documented
@Constraint(validatedBy = FranceValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface CountryConstraint {
    String message() default "The User must be reside in France";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}