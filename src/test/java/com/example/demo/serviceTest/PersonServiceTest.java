package com.example.demo.serviceTest;


import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService service;


    @Test
    public void findById() {
        // when
        final Person opt = service.findByUserNameLike("Karam");

        assertEquals(opt.getId(), "");
    }
}
