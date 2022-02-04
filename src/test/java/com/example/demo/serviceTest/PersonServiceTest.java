package com.example.demo.serviceTest;


import com.example.demo.dao.PersonRepository;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import javax.transaction.Transactional;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PersonServiceTest {
    @Autowired
    private PersonService service;
    @Autowired
    private PersonRepository repository;


    @Test
    @Transactional
    public void findByUserNameLikeTest() {

        // when
        final Person per = service.findByUserNameLike("Karam");
        assertEquals(per.getId().longValue(), 1L);
    }

    @Test
    @Transactional
    public void saveTest() {
        // when
        final Person per = service.findByUserNameLike("Karam");
        per.setUserName("Jose");

        service.save(per);
        assertEquals(per.getId().longValue(), 1L);
        assertEquals(per.getUserName(), "Jose");
    }

    @Test
    @Transactional
    public void olderAndFrenchTest() throws ParseException {
        String string = "January 2, 2020";
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date = format.parse(string);
        System.out.println(date);
        // when
        final Person per = service.findByUserNameLike("Karam");
        //the user is under 18
        per.setDateOfBirth(date);

        final Person per2 = service.findByUserNameLike("Antoine");
        // not reside in France
        per2.setCountry("Italie");

        service.save(per);
        service.save(per2);
        boolean is=service.olderAndFrench(per);
        boolean is2=service.olderAndFrench(per2);
        assertEquals(per.getId().longValue(), 1L);
        assertEquals(per.getUserName(), "Karam");
        assertEquals(per.getUserName(), "Karam");
        assertFalse(is);
        assertFalse(is2);
    }


    @Test
    @Transactional
    public void requiredFieldsTest() {
        // when
        final Person per = service.findByUserNameLike("Karam");
        per.setCountry("");
        repository.save(per);
        boolean is=service.requiredFields(per);
        assertEquals(per.getId().longValue(), 1L);
        assertEquals(per.getUserName(), "Karam");
        assertTrue(is);
    }

    @Test
    @Transactional
    public void existsByUserNameTest() {
        // when
        final Person per = service.findByUserNameLike("Karam");
        boolean is=service.existsByUserName(per.getUserName());
        assertEquals(per.getId().longValue(), 1L);
        assertEquals(per.getUserName(), "Karam");
        assertTrue(is);
    }




}
