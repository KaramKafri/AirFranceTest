package com.example.demo.mapper;

import com.example.demo.dto.PersonDto;
import com.example.demo.model.Person;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

/**
 * Mapper between person and personDto
 */
@Mapper(componentModel = "spring")
public interface PersonMapper {
   PersonDto toDto (Person person);
   @Mapping(target = "dateOfBirth", dateFormat = "yyyy-MM-dd")
   Person to (PersonDto personDto);



}
