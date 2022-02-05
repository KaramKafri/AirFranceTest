package com.example.demo.controllerTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import com.example.demo.controller.PersonController;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.mapper.PersonMapperImpl;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import static org.mockito.ArgumentMatchers.any;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.text.SimpleDateFormat;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(PersonController.class)
@Import({ PersonMapperImpl.class })
public class PersonControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @Autowired
    private PersonMapper personMapper;
    @MockBean
    private PersonService personService;

    @Autowired
    private ObjectMapper jsonConverter;

    @Test
    public void shouldReturnOKWhenGetUserByNameTest() throws Exception {
        Person person=new Person("person1",new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998"),"FRANCE","0651460593","M");
        person.setId(1L);
        when(personService.existsByUserName(person.getUserName())).thenReturn(true);
        when(personService.findByUserNameLike(person.getUserName())).thenReturn(person);

        // when
        final ResultActions ra = mockMvc.perform(get("/getUserByName/person1")).andDo(print());

        // then
        ra.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string(jsonConverter.writeValueAsString(personMapper.toDto(person))));
    }

    @Test
    public void shouldReturn404WhenGetUserByNameTest() throws Exception {
        Person person=new Person("person1",new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1998"),"FRANCE","0651460593","M");
        person.setId(1L);
        when(personService.existsByUserName(person.getUserName())).thenReturn(false);
        when(personService.findByUserNameLike(person.getUserName())).thenReturn(person);

        // when
        final ResultActions ra = mockMvc.perform(get("/getUserByName/person1")).andDo(print());

        // then
        ra.andExpect(status().isNotFound()).andExpect(MockMvcResultMatchers.content().string("there is no name for this user"));
    }

    @Test
    public void shouldReturnOkWhenRegisterNewUserTest() throws Exception {
        Person person=new Person("person1",new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1993"),"FRANCE","0651460593","M");
        person.setId(1L);
        when(personService.olderAndFrench(any())).thenReturn(true);
        final ResultActions ra = mockMvc.perform(post("/newUser").content(jsonConverter.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print());
        // then
        ra.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.content().string("the User has been registered"));


    }
    @Test
    public void shouldReturn400WhenRegisterNewUserTest() throws Exception {
        Person person=new Person("person1",new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1993"),"FRANCE","0651460593","M");
        person.setId(1L);
        when(personService.requiredFields(any())).thenReturn(true);
        final ResultActions ra = mockMvc.perform(post("/newUser").content(jsonConverter.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print());
        // then
        ra.andExpect(status().isBadRequest()).andExpect(MockMvcResultMatchers.content().string("One of three required fields(userName,dateOfBirth,country) is empty"));


    }
    @Test
    public void shouldReturn302WhenRegisterNewUserTest() throws Exception {
        Person person=new Person("person1",new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1993"),"FRANCE","0651460593","M");
        person.setId(1L);
        when(personService.existsByUserName(any())).thenReturn(true);
        final ResultActions ra = mockMvc.perform(post("/newUser").content(jsonConverter.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print());
        // then
        ra.andExpect(status().isFound()).andExpect(MockMvcResultMatchers.content().string("The name of the User already exists"));


    }

    @Test
    public void shouldReturn406WhenRegisterNewUserTest() throws Exception {
        Person person=new Person("person1",new SimpleDateFormat("dd/MM/yyyy").parse("31/12/1993"),"FRANCE","0651460593","M");
        person.setId(1L);
        when(personService.olderAndFrench(any())).thenReturn(false);
        final ResultActions ra = mockMvc.perform(post("/newUser").content(jsonConverter.writeValueAsString(person)).contentType(MediaType.APPLICATION_JSON_VALUE)
                .accept(MediaType.APPLICATION_JSON_VALUE)).andDo(print());
        // then
        ra.andExpect(status().isNotAcceptable()).andExpect(MockMvcResultMatchers.content().string("The User must be over 18 years old and reside in France"));


    }}