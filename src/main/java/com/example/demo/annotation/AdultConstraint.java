package com.example.demo.annotation;


import com.example.demo.validator.AdultValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 * Annotation for validator the old of the user
 */
@Documented
@Constraint(validatedBy = AdultValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface AdultConstraint {
    String message() default "The User must be over 18 years";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}