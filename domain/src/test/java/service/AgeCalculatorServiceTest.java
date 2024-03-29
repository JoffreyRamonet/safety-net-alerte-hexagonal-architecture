package service;


import com.domain.dto.NamesDTO;
import com.domain.model.Person;
import com.domain.service.AgeCalculatorService;
import com.domain.service.MedicalrecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.domain.stub.MedicalrecordRepositoryStub;
import com.domain.stub.PersonRepositoryStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AgeCalculatorServiceTest {
    
    MedicalrecordRepositoryStub medicalrecordRepository;
    MedicalrecordService medicalrecordService;
    AgeCalculatorService ageCalculatorService;
    PersonRepositoryStub personRepository;
    
    @BeforeEach
    void setUp() {
        medicalrecordRepository = new MedicalrecordRepositoryStub();
        medicalrecordService = new MedicalrecordService(medicalrecordRepository);
        ageCalculatorService = new AgeCalculatorService(medicalrecordService);
        personRepository = new PersonRepositoryStub();
    }
    
    @Test
    void shouldCountTheAdultNumberFromAListOfPersonTest(){
        List<Person> persons = personRepository.getAll();
        
        int result = ageCalculatorService.counterOfAdultListFilter(persons);
        
        assertEquals(3, result);
    }
    
    @Test
    void shouldFilterAdultsFromAListOfPersonAndReturnAListOfMinorTest(){
        List<Person> persons = personRepository.getAll();
        
        List<Person> result = ageCalculatorService.adultListFilter(persons);
        
        assertEquals(2, result.size());
    }
    
    @Test
    void shouldReturnTheAgeOfAPersonTest(){
        String firstname = "Roger";
        String lastname = "Boyd";
        NamesDTO namesDTO = new NamesDTO(firstname, lastname);
        
        int result = ageCalculatorService.ageCalculator(namesDTO);
        
        assertEquals(6, result);
    }
}
