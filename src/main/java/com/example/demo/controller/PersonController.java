package com.example.demo.controller;

import com.example.demo.dto.PersonDto;
import com.example.demo.mapper.PersonMapper;
import com.example.demo.model.Person;
import com.example.demo.service.PersonService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller
 */
@RestController
public class PersonController {
    @Autowired
    private PersonService personService;
    @Autowired
    private PersonMapper personMapper;


    /**
     * A REST API to displays the details of a registered user by his name
     * @param name the name of the user
     * @return 404 in case there is no name for this user with a message
     *         200 in case it's OK
     */
    @GetMapping(value = "/getUserByName/{name}" ,produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity getUserByName(@PathVariable("name") final String name){
        if(!personService.existsByUserName(name)){
            return new ResponseEntity<>(
                    "there is no name for this user",
                    HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(
                personMapper.toDto(personService.findByUserNameLike(name)),
                HttpStatus.OK);


    }

    /**
     *A REST API to register a new user
     * @param person person object
     * @return  400 in case one of three required fields(userName,dateOfBirth,country) is null or empty
     *          302 in case the name of the user already exists
     *          406 in case the user is under 18 or does not reside in France
     *          200 in case it is OK
     */
    @PostMapping(value = "/newUser",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity registerNewUser(@RequestBody final PersonDto person){
        Person User = personMapper.to(person);

        if(personService.requiredFields(User)){
            return new ResponseEntity<>(
                    "One of three required fields(userName,dateOfBirth,country) is empty",
                    HttpStatus.BAD_REQUEST);
        }
        else if(personService.existsByUserName(person.getUserName())){
            return new ResponseEntity<>(
                    "The name of the User already exists",
                    HttpStatus.FOUND);
        }
        else if(!personService.olderAndFrench(User)){
            return new ResponseEntity<>(
                    "The User must be over 18 years old and reside in France",
                    HttpStatus.NOT_ACCEPTABLE);
        }
        personService.save(User);
        return new ResponseEntity<>(
                "the User has been registered",
                HttpStatus.OK);

    }


}
