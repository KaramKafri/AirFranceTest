package com.example.demo.dao;

import com.example.demo.model.Person;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface PersonRepository extends CrudRepository<Person, Long> {

     Optional<Person> findByUserNameLike(String name);
     Boolean existsByUserName(String userName);
}
