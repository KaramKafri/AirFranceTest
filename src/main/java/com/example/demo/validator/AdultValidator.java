package com.example.demo.validator;


import com.example.demo.annotation.AdultConstraint;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;

/**
 * ConstraintValidator the user older than 18 years old
 */
public class AdultValidator implements
        ConstraintValidator<AdultConstraint, String> {

    @Override
    public void initialize(AdultConstraint adult) {
    }

    @Override
    public boolean isValid(String contactField,
                           ConstraintValidatorContext cxt) {
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        int age = 0;
        sdf.setLenient(false);
        if(contactField !=null) {
            try {
                age = Period.between(new Date(sdf.parse(contactField).getTime()).toLocalDate(), LocalDate.now()).getYears();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            return age>=18;
        }

        return true;
    }

}
