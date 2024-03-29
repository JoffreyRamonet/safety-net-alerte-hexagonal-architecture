package service;


import com.domain.dto.NamesDTO;
import com.domain.model.Person;
import com.domain.service.PersonService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.domain.stub.PersonRepositoryStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PersonServiceTest {
    
        PersonRepositoryStub personRepository;
        PersonService personService;
    
    private final String firstName = "Roger";
    private final String lastName = "Boyd";
    private final NamesDTO namesDTO = new NamesDTO(firstName, lastName);
    
        @BeforeEach
        void setUp(){
            personRepository = new PersonRepositoryStub();
            personService = new PersonService(personRepository);
        }
        
    @Test
    void shouldReturnAllPersonTest(){
        
        List<Person> result = personService.getAll();
        
        assertEquals(5, result.size());
        
    }
    
    @Test
    void shouldReturnAPersonWithFirstnameAndLastnameTest(){
        Person result = personService.getByNames(namesDTO);
        
        assertTrue(result.getFirstName().equals(firstName) && result.getLastName().equals(lastName));
    }
    
    @Test
    void shouldSaveANewPersonTest(){
        Person toSave = new Person("Firstname", "Lastname", "address", "city", "zip", "phone", "email");
        
        Person result = personService.save(toSave);
        
        int resultSize = personService.getAll()
                .size();
        
        assertEquals("Firstname", result.getFirstName());
        assertEquals(6, resultSize);
    }
    
    @Test
    void shouldUpdateAPersonTest(){
        Person toUpdate = new Person(null, null, "newAddress", "newCity", "newZip", "newPhone", "newEmail");
        
        Person result = personService.update(toUpdate, namesDTO);
        
        assertEquals("newAddress", result.getAddress());
    }
    
    @Test
    void shouldDeleteAPersonTest(){
        personService.deleteByNames(namesDTO);
        
        assertDoesNotThrow(() -> personService.deleteByNames(namesDTO));
    }
}
