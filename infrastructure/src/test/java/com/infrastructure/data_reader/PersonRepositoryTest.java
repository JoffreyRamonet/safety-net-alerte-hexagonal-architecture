package com.infrastructure.data_reader;

import com.infrastructure.data_reader.repository.PersonRepository;
import com.domain.dto.NamesDTO;
import com.domain.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class PersonRepositoryTest {
    
    PersonRepository personRepository;
    
    private final String firstName = "Roger";
    private final String lastName = "Boyd";
    private final NamesDTO namesDTO = new NamesDTO(firstName, lastName);
    
    @BeforeEach
    void setUp(){
        JsonReader.setFilepath("/dataStub.json");
        personRepository = new PersonRepository();
    }
    
    @Test
    void shouldReturnAllPersonsTest(){
        List<Person> result = personRepository.getAll();

        assertEquals(8, result.size());
    }
    
    @Test
    void shouldReturnAPersonWithNamesTest(){
        Person result = personRepository.getByNames(namesDTO);
        
        assertEquals("841-874-6512", result.getPhone());
    }
    
    @Test
    void shouldReturnAIstOfPersonFromAddressTest(){
        String address = "1509 Culver St";
        
        List<Person> result = personRepository.getByAddress(address);
        
        assertEquals(5, result.size());
    }
    
    @Test
    void shouldSaveANewPersonTest(){
        Person person = new Person("firstName", "lastName", "address", "city", "zip", "phone", "email");
        
        personRepository.save(person);
        
        List<Person> result = personRepository.getAll();
        
        assertEquals(9, result.size());
    }
    
    @Test
    void shouldUpdatePersonTest(){
        Person person = new Person("firstName", "lastName", "address", "city", "zip", "phone", "email");
        
        Person result = personRepository.update(person, namesDTO);
        
        assertEquals(person, result);
    }
    
    @Test
    void shouldDeleteAPersonTest(){
        personRepository.deleteByNames(namesDTO);
        
        List<Person> result = personRepository.getAll();
        
        assertEquals(7, result.size());
    }
}
