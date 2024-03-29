package service;


import com.domain.model.Person;
import com.domain.service.AddressCalculatorService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.domain.stub.FirestationRepositoryStub;
import com.domain.stub.PersonRepositoryStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AddressCalculatorServiceTest {
    
    FirestationRepositoryStub firestationRepository;
    PersonRepositoryStub personRepository;
    AddressCalculatorService addressCalculatorService;
    
    @BeforeEach
    public void setUp(){
        firestationRepository = new FirestationRepositoryStub();
        personRepository = new PersonRepositoryStub();
        addressCalculatorService = new AddressCalculatorService(firestationRepository, personRepository);
    }
    
    @Test
    void shouldReturnAllPersonsCoveredByAFirestationTest(){
        
        String station = "3";
        
        List<Person> result = addressCalculatorService.personsCoveredByAFirestation(station);
        
        assertEquals(4, result.size());
    }
}
