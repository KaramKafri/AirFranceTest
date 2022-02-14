package com.example.demo.validator;


import com.example.demo.annotation.CountryConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *ConstraintValidator for the user reside in france
 */
public class FranceValidator implements
        ConstraintValidator<CountryConstraint, String> {

    @Override
    public void initialize(CountryConstraint country) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {

        return contactField==null ||contactField.equalsIgnoreCase("FRANCE");
    }

}
