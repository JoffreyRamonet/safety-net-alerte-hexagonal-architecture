package com.infrastructure.controller;

import com.domain.api.PersonApi;
import com.domain.dto.NamesDTO;
import com.domain.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class to perform request on the uri /api/v1/person.
 *
 * Call personApi to get data to display.
 * @see PersonApi
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/person")
public class PersonController {

    private final PersonApi personApi;
    
    public PersonController(PersonApi personApi) {
        this.personApi = personApi;
    }
    
    @GetMapping
    public List<Person> getAll() {
        return personApi.getAll();
    }

    @GetMapping("/{firstName}/{lastName}")
    public Person findBy(
            @PathVariable final String firstName,
            @PathVariable final String lastName) {
        log.debug("Variable parsed: " + firstName + " " + lastName);
        return personApi.getByNames(new NamesDTO(firstName, lastName));
    }
    
    @PostMapping("/add")
    public Person save(
            @RequestBody Person person) {
        log.debug(
                "The Person parsed: " + person.getFirstName() + " " + person.getLastName() + " " + person.getAddress() +
                        " " + person.getCity() + " " + person.getZip() + " " + person.getPhone() + " " +
                        person.getEmail());
        return personApi.save(person);
    }

    @PatchMapping("/update/{firstName}/{lastName}")
    public Person update(
            @RequestBody Person person,
            @PathVariable final String firstName,
            @PathVariable final String lastName) {
        log.debug(
                "The Person parsed: " + person.getFirstName() + " " + person.getLastName() + " " + person.getAddress() +
                        " " + person.getCity() + " " + person.getZip() + " " + person.getPhone() + " " +
                        person.getEmail());
        log.debug("Variable parsed: " + firstName + " " + lastName);
        return personApi.update(person, new NamesDTO(firstName, lastName));
    }
    
    @DeleteMapping("/delete/{firstName}/{lastName}")
    public void deleteBy(
            @PathVariable final String firstName,
            @PathVariable final String lastName) {
        log.debug("Variable parsed: " + firstName + " " + lastName);
        personApi.deleteByNames(new NamesDTO(firstName, lastName));
    }
}

