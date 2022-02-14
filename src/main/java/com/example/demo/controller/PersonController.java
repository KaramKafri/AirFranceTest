package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;

/**
 * Controller
 */
@RestController
public class PersonController {

    private final PersonService personService;

    private final PersonMapper personMapper;

    public PersonController(PersonService personService, PersonMapper personMapper) {
        this.personService = personService;
        this.personMapper = personMapper;
    }

    /**
     * A REST API to displays the details of a registered user by his name
     * @param name the name of the user
     * @return PersonDto object of type PersonDto
     *
     */

    @GetMapping(value = "/users/{name}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(HttpStatus.OK)
    public PersonDto getUserByName(@PathVariable("name") final String name){
        return personMapper.toDto(personService.findByUserNameLike(name));

    }

    /**
     *A REST API to register a new user
     * @param person person object
     * @return PersonDto
     */
    @PostMapping(value = "/users",produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseStatus(value = HttpStatus.CREATED)
    public PersonDto registerNewUser(@RequestBody @Valid final PersonDto person){

        Person newPerson= personService.save(personMapper.to(person));
        return personMapper.toDto(newPerson);
    }


}
