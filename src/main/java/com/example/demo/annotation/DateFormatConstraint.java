package com.example.demo.annotation;


import com.example.demo.validator.DateOfBirthValidator;
import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

/**
 *Annotation for validator the Date format of the day of birthday of the user
 */
@Documented
@Constraint(validatedBy = DateOfBirthValidator.class)
@Target( { ElementType.METHOD, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
public @interface DateFormatConstraint {
    String message() default "The valid format of the day of birthday 'yyyy-MM-dd'";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}