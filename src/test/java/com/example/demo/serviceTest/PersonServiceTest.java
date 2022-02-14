package com.example.demo.serviceTest;


import com.example.demo.dao.PersonRepository;
import com.example.demo.exception.NameNotFoundException;
import com.example.demo.exception.UserExistException;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

/**
 *
 */
@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {


    @Mock
    private PersonRepository repository;
    private PersonService service;
    private Person person;
    @BeforeEach
    public void setUp() throws NameNotFoundException, ParseException {
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        person=new Person("newName",date1,"FRANCE","0651460593","M");
        service= new PersonService(repository);
    }

    /**
     *
     */
    @Test
    public void findByUserNameLikeTest() {

        when(repository.findByUserNameLike(any())).thenReturn(java.util.Optional.of(person));
        Person personSaved =service.findByUserNameLike(person.getUserName());
        assertEquals(personSaved,person);

    }

    /**
     *
     */
    @Test
    public void findByUserNameLikeExceptionTest() {

        assertThrows(NameNotFoundException.class, () -> service.findByUserNameLike("newName"));

    }

    /**
     *
     * @throws NameNotFoundException in case there no name of the user
     */
    @Test
    public void saveTest() throws  NameNotFoundException {

        when(service.existsByUserName(any())).thenReturn(false);
        service.save(person);
        assertEquals(person.getUserName(),"newName");

    }

    /**
     *
     */
    @Test
    public void existsByUserNameTest() {

        when(repository.existsByUserName(any())).thenReturn(false);
        boolean existsByUserName =service.existsByUserName(person.getUserName());
        assertFalse(existsByUserName);

    }

    /**
     *
     * @throws UserExistException in case the user already exists
     */
    @Test
    public void existsByUserNameExceptionTest() throws UserExistException {

        assertThrows(UserExistException.class, () ->{  when(repository.existsByUserName(any())).thenReturn(true);
            service.existsByUserName("newName");} );


    }


}
