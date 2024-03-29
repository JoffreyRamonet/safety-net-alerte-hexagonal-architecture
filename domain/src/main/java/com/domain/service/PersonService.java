package com.domain.service;


import com.domain.api.PersonApi;
import com.domain.ddd.DomainService;
import com.domain.dto.NamesDTO;
import com.domain.model.Person;
import com.domain.spi.PersonSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class to perform Person business treatments.
 * Implements PersonApi to send responses to controllers in the infrastructure layer.
 *
 * @see PersonApi
 * <p>
 * Use the PersonSpi to get data from the infrastructure layer.
 * @see PersonSpi
 * <p>
 * Annotated with @DomainService to be considered a Bean by the infrastructure layer.
 * @see DomainService
 */
@DomainService
public class PersonService implements PersonApi {
    
    private final static Logger logger = LoggerFactory.getLogger(PersonService.class);
    
    private final PersonSpi personSpi;
    
    public PersonService(PersonSpi personSpi) {
        this.personSpi = personSpi;
    }
    
    @Override
    public List<Person> getAll() {
        return personSpi.getAll();
    }
    
    @Override
    public Person getByNames(NamesDTO namesDTO) {
        logger.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        return personSpi.getByNames(namesDTO);
    }
    
    @Override
    public Person save(Person person) {
        logger.debug(
                "The Person parsed: " + person.getFirstName() + " " + person.getLastName() + " " + person.getAddress() +
                        " " + person.getCity() + " " + person.getZip() + " " + person.getPhone() + " " +
                        person.getEmail());
        return personSpi.save(person);
    }
    
    @Override
    public Person update(Person person, NamesDTO namesDTO) {
        
        Person personToUpdated = personSpi.getByNames(namesDTO);
        
        if(person.getFirstName() != null)
            personToUpdated.setFirstName(person.getFirstName());
        if(person.getLastName() != null)
            personToUpdated.setLastName(person.getLastName());
        if(person.getAddress() != null)
            personToUpdated.setAddress(person.getAddress());
        if(person.getCity() != null)
            personToUpdated.setCity(person.getCity());
        if(person.getZip() != null)
            personToUpdated.setZip(person.getZip());
        if(person.getPhone() != null)
            personToUpdated.setPhone(person.getPhone());
        if(person.getEmail() != null)
            personToUpdated.setEmail(person.getEmail());
        
        logger.debug(
                "The Person parsed: " + person.getFirstName() + " " + person.getLastName() + " " + person.getAddress() +
                        " " + person.getCity() + " " + person.getZip() + " " + person.getPhone() + " " +
                        person.getEmail());
        logger.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        return personSpi.update(personToUpdated, namesDTO);
    }
    
    @Override
    public void deleteByNames(NamesDTO namesDTO) {
        logger.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        personSpi.deleteByNames(namesDTO);
    }
}
