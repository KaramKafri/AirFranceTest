package com.example.demo.validator;


import com.example.demo.annotation.DateFormatConstraint;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *ConstraintValidator for the format of the day of birthday
 */
public class DateOfBirthValidator implements
        ConstraintValidator<DateFormatConstraint, String> {

    @Override
    public void initialize(DateFormatConstraint dateOfBirth) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        if(contactField !=null){
            try {
                sdf.parse(contactField);
            } catch (ParseException e) {

                return false;
            }
        }
        return true;
    }

}
