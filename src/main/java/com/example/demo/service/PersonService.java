package com.example.demo.service;

import com.example.demo.dao.PersonRepository;
import com.example.demo.exception.NameNotFoundException;
import com.example.demo.exception.UserExistException;
import com.example.demo.model.Person;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
/**
 * Service for the user
 */
@Service
@Transactional
public class PersonService {

    private final PersonRepository repo;

    public PersonService(PersonRepository repo) {
        this.repo = repo;
    }

    /**
     * A service to search for the user by name
     *
     * @param name name of user
     * @return person object in case its ok , error in case there no name
     */
    public Person findByUserNameLike(final String name) {
        if(repo.findByUserNameLike(name).isEmpty()){
            throw new NameNotFoundException();
        }
        return  repo.findByUserNameLike(name).get();
    }

    /**
     * A service to register a new user
     * @param user Person Object
     */
    public Person save(Person user)  {
        existsByUserName(user.getUserName());
        return repo.save(user);
    }

    /**
     *A service to check if the name of the user already exists
     * @param userName name of the user
     * @return false or error in case the name already exists
     */

    public boolean existsByUserName(String userName) {
        if( repo.existsByUserName(userName)){
            throw new UserExistException();
        }
        return false;
    }
}
