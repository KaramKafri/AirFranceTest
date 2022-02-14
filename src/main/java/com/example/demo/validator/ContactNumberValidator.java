package com.example.demo.validator;


import com.example.demo.annotation.ContactNumberConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 *ConstraintValidator for the format of ContactNumber
 */
public class ContactNumberValidator implements
        ConstraintValidator<ContactNumberConstraint, String> {

    @Override
    public void initialize(ContactNumberConstraint contactNumber) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        return contactField == null || (contactField.matches("[0-9]+")
                && (contactField.length() > 8) && (contactField.length() < 14));
    }

}
