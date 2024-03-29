package com.infrastructure.data_reader.repository;

import com.infrastructure.data_reader.JsonReader;
import com.domain.dto.NamesDTO;
import com.domain.model.Person;
import com.domain.spi.PersonSpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository of the model Person.
 * Implement the PersonSpi to give data from the JsonFile to the domain.
 * Annotated with @Primary cause of repositories of the JsonReader client are the default choice.
 *
 * @see Primary
 * @see PersonSpi
 * @see JsonReader
 */
@Slf4j
@Repository
@Primary
public class PersonRepository implements PersonSpi {
    
    /**
     * Call the JsonReader to create an InMemory list of Person.
     */
    private final List<Person> persons = JsonReader.personsList();
    
    /**
     * cRud method - read all.
     *
     * @return a list of all Person.
     */
    @Override
    public List<Person> getAll() {
        return persons;
    }
    
    /**
     * cRud method - read  by namesDTO.
     *
     * @param namesDTO contains String firstName and String lastName.
     * @return a Person with the firstName and the lastName of the NamesDto parsed.
     */
    @Override
    public Person getByNames(NamesDTO namesDTO) {
        log.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        return persons.stream()
                .filter(person -> person.getFirstName()
                        .equals(namesDTO.firstName()) && person.getLastName()
                        .equals(namesDTO.lastName()))
                .toList()
                .get(0);
    }
    
    /**
     * cRud method - read  by address.
     *
     * @param address attribute to found Persons.
     * @return a List of Person with the address parsed.
     */
    @Override
    public List<Person> getByAddress(String address) {
        log.debug("The address parsed: " + address);
        return persons.stream()
                .filter(person -> person.getAddress()
                        .equals(address))
                .toList();
    }
    
    /**
     * Crud method - create a new Person.
     * CAUTION - the Person is saved in memory and not writing in the JsonFile!
     *
     * @param person Person parsed to be saved.
     * @return the Person saved.
     */
    @Override
    public Person save(Person person) {
        log.debug(
                "The Person parsed: " + person.getFirstName() + " " + person.getLastName() + " " + person.getAddress() +
                        " " + person.getCity() + " " + person.getZip() + " " + person.getPhone() + " " +
                        person.getEmail());
        persons.add(person);
        return person;
    }
    
    /**
     * crUd method - update by NamesDto.
     * CAUTION - the Person is updated in memory and not updated in the JsonFile!
     *
     * @param person   Person with attributes have to be modified.
     * @param namesDTO contains String firstName and String lastName.
     * @return the Person object modified.
     */
    @Override
    public Person update(Person person, NamesDTO namesDTO) {
        for(int i = 0; i < persons.size(); i++) {
            if(persons.get(i)
                    .getFirstName()
                    .equals(namesDTO.firstName()) && persons.get(i)
                    .getLastName()
                    .equals(namesDTO.lastName()))
                persons.set(i, person);
        }
        log.debug(
                "The Person parsed: " + person.getFirstName() + " " + person.getLastName() + " " + person.getAddress() +
                        " " + person.getCity() + " " + person.getZip() + " " + person.getPhone() + " " +
                        person.getEmail());
        log.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        return person;
    }
    
    /**
     * cruD delete by NamesDto.
     * CAUTION - the Person is deleted in memory and not deleted in the JsonFile!
     *
     * @param namesDTO contains String firstName and String lastName.
     */
    @Override
    public void deleteByNames(NamesDTO namesDTO) {
        log.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        persons.removeIf(person -> person.getFirstName()
                .equals(namesDTO.firstName()) && person.getLastName()
                .equals(namesDTO.lastName()));
    }
}
