package com.domain.stub;


import com.domain.ddd.Stub;
import com.domain.dto.NamesDTO;
import com.domain.model.Person;
import com.domain.spi.PersonSpi;
import com.domain.stub.test_impl.DataStub;

import java.util.List;

@Stub
public class PersonRepositoryStub implements PersonSpi {

    private final DataStub data = new DataStub();
    private final List<Person> persons = data.persons();
    
    @Override
    public List<Person> getAll() {
        return persons;
    }
    
    @Override
    public Person getByNames(NamesDTO namesDTO) {
        return persons
                .stream()
                .filter(person -> person.getFirstName()
                        .equals(namesDTO.firstName()) && person.getLastName()
                        .equals(namesDTO.lastName()))
                .toList()
                .get(0);
    }
    
    @Override
    public List<Person> getByAddress(String address) {
        return persons
                .stream()
                .filter(person -> person.getAddress()
                        .equals(address))
                .toList();
    }
    
    @Override
    public Person save(Person person) {
        persons.add(person);
        return person;
    }
    
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
        return person;
    }
    
    @Override
    public void deleteByNames(NamesDTO namesDTO) {
        persons.removeIf(person -> person.getFirstName().equals(namesDTO.firstName()) && person.getLastName().equals(namesDTO.lastName()));
    }
}
