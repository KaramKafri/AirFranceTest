package com.example.demo.dto;


import com.example.demo.annotation.AdultConstraint;
import com.example.demo.annotation.ContactNumberConstraint;
import com.example.demo.annotation.CountryConstraint;
import com.example.demo.annotation.DateFormatConstraint;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.Date;


/**
 * PersonDto for the table of "Person"
 */
public class PersonDto {
    private Long id;

    @NotEmpty
    @NotNull(message = "userName must not be null")
    private String userName;

    @DateFormatConstraint
    @AdultConstraint
    @NotNull(message = "dateOfBirth must not be null")
    private String dateOfBirth;

    @NotNull(message = "country must not be null")
    @CountryConstraint
    private String country;

    @ContactNumberConstraint
    private String phoneNumber;

    private String gender;

    public PersonDto(String userName, String dateOfBirth, String country, String phoneNumber, String gender) {
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
