package com.example.demo.controllerTest;


import com.example.demo.controller.PersonController;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonControllerTest {
    @Autowired
    private PersonController controller;


    @Test
    public void findById() {

        // then


    }
}
