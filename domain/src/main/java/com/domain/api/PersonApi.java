package com.domain.api;


import com.domain.dto.NamesDTO;
import com.domain.model.Person;

import java.util.List;

/**
 * Interface to perform requests from controllers to the domain for the Person model.
 *
 * @see Person
 * @see com.domain.service.PersonService
 */
public interface PersonApi {
    
    List<Person> getAll();
    
    Person getByNames(NamesDTO namesDTO);
    
    Person save(Person person);
    
    Person update(Person person, NamesDTO namesDTO);
    
    void deleteByNames(NamesDTO namesDTO);
}
