package com.example.demo.model;

import javax.persistence.*;
import java.util.Date;

/**
 * table in name "Person"
 */
@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Id", nullable = false)
    private Long id;

    @Column(name = "User_Name", length = 20, nullable = false)
    private String userName;

    @Temporal(TemporalType.DATE)
    @Column(name = "Date_Of_Birth", nullable = false)
    private Date dateOfBirth;

    @Column(name = "Country", length = 64, nullable = false)
    private String country;

    @Column(name = "Phone", length = 15)
    private String phoneNumber;

    @Column(name = "Gender")
    private String gender;

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

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
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

    public Person(String userName, Date dateOfBirth, String country, String phoneNumber, String gender) {
        this.userName = userName;
        this.dateOfBirth = dateOfBirth;
        this.country = country;
        this.phoneNumber = phoneNumber;
        this.gender = gender;
    }

    public Person() {
    }
}
