package com.example.demo.controllerTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import com.example.demo.controller.PersonController;
import com.example.demo.dto.PersonDto;
import com.example.demo.exception.NameNotFoundException;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Mock
    private PersonMapper personMapper;
    @Mock
    private PersonService personService;
    private PersonController personController;
    private Person person;
    private PersonDto personDto;
    @BeforeEach
    public void setUp() throws NameNotFoundException, ParseException {
        String sDate1="31/12/1998";
        Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(sDate1);
        person=new Person("newName",date1,"FRANCE","0651460593","M");
        personDto = new PersonDto("newName", "1998-12-31", "FRANCE", "0651460593", "M");
      personController=new PersonController(personService,personMapper);
      this.mockMvc = MockMvcBuilders.standaloneSetup(personController).build();

    }
    @Test
    public void shouldReturnOKWhenGetUserByNameTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(personService.findByUserNameLike("newName")).thenReturn(person);
        when(personMapper.toDto(person)).thenReturn(personDto);
        PersonDto personDtoControler=personController.getUserByName("newName");
        assertEquals(personDtoControler,personDto);

    }

    @Test
    public void shouldReturnOkWhenRegisterNewUserTest() {
        MockHttpServletRequest request = new MockHttpServletRequest();
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));
        when(personMapper.to(personDto)).thenReturn(person);
        when(personService.save(person)).thenReturn(person);
        when(personMapper.toDto(person)).thenReturn(personDto);

        assertEquals(personController.registerNewUser(personDto), personDto);


    }
   }