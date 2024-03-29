package com.domain.spi;

import com.domain.dto.NamesDTO;
import com.domain.model.Person;

import java.util.List;

/**
 * Interface to perform requests to the client in the infrastructure layer from the domain for Person model.
 *
 * @see Person
 * @see com.domain.service.PersonService
 * @see com.domain.service.SpecialsRequestsService
 */
public interface PersonSpi {
    List<Person> getAll();
    
    Person getByNames(NamesDTO namesDTO);
    
    List<Person> getByAddress(String address);
    
    Person save(Person person);
    
    Person update(Person person, NamesDTO namesDTO);
    
    void deleteByNames(NamesDTO namesDTO);
}
