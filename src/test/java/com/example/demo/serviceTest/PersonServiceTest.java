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
    public void findByUserNameLikeTest() throws ParseException {
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        Person person=new Person("newName",date1,"FRANCE","0651460593","M");
        repository.save(person);
        // when
        final Person per = service.findByUserNameLike(person.getUserName());
        assertEquals(per.getId(), person.getId());
    }

    @Test
    @Transactional
    public void saveTest() throws ParseException {
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        Person person=new Person("newName",date1,"FRANCE","0651460593","M");

        // when
        service.save(person);
        final Person per = repository.findByUserNameLike(person.getUserName());

        //then
        assertEquals(per.getId(),person.getId());
        assertEquals(per.getUserName(), person.getUserName());
    }

    @Test
    @Transactional
    public void olderAndFrenchTest() throws ParseException {
        String string = "January 2, 2020";
        DateFormat format = new SimpleDateFormat("MMMM d, yyyy", Locale.ENGLISH);
        Date date1 = format.parse(string);
        Person person1=new Person("person1",date1,"FRANCE","0651460593","M");


        String sDate1="31/12/1998";
        Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        Person person2=new Person("person2",date2,"ITALY","0651460593","M");

        // when
        repository.save(person1);
        repository.save(person2);
        boolean isOld=service.olderAndFrench(person1);
        boolean isFrance=service.olderAndFrench(person2);

        assertFalse(isOld);
        assertFalse(isFrance);
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
        boolean isexists=service.existsByUserName(per.getUserName());
        assertEquals(per.getId().longValue(), 1L);
        assertEquals(per.getUserName(), "Karam");
        assertTrue(isexists);
    }




}
