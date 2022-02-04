package com.example.demo.service;

import com.example.demo.dao.PersonRepository;
import com.example.demo.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import javax.transaction.Transactional;
import java.sql.Date;
import java.time.LocalDate;
import java.time.Period;
import java.util.Objects;


/**
 * Service
 */
@Service
@Transactional
public class PersonService {
    @Autowired
    private PersonRepository repo;

    /**
     * A service to search for the user by name
     *
     * @param name name of user
     * @return person object
     */
    public Person findByUserNameLike(final String name) {
        return  repo.findByUserNameLike(name);
    }

    /**
     * A service to register a new user
     * @param user Person Object
     */
    public void save(Person user)  {
        Objects.requireNonNull(user, "L'objet User ne doit pas être null");
        repo.save(user);
    }

    /**
     * A service to check if the user is under 18 or does not reside in France
     * @param user person Object
     * @return true ou false
     */
    public Boolean olderAndFrench(Person user)  {
        int age = Period.between(new Date(user.getDateOfBirth().getTime()).toLocalDate(), LocalDate.now()).getYears();
        return age >= 18 && user.getCountry().equals("FRANCE");
    }

    /**
     *  A service to check if one of three required fields(userName,dateOfBirth,country) is empty
     * @param user person Object
     * @return true or false
     */
    public Boolean requiredFields(Person user)  {

        return user.getUserName().isEmpty() || user.getCountry().isEmpty() || user.getDateOfBirth() == null;
    }

    /**
     *A service to check if the name of the user already exists
     * @param userName name of the user
     * @return true or false
     */
    public boolean existsByUserName(String userName) {
        return repo.existsByUserName(userName);
    }
}
