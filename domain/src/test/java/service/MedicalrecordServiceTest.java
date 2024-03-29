package service;


import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;
import com.domain.service.MedicalrecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.domain.stub.MedicalrecordRepositoryStub;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class MedicalrecordServiceTest {
    
    private MedicalrecordRepositoryStub medicalrecordRepository;
    private MedicalrecordService medicalrecordService;
    
    private final String firstName = "Roger";
    private final String lastName = "Boyd";
    private final NamesDTO namesDTO = new NamesDTO(firstName, lastName);
    
    
    @BeforeEach
    void setUp(){
        medicalrecordRepository = new MedicalrecordRepositoryStub();
        medicalrecordService = new MedicalrecordService(medicalrecordRepository);
    }
    
    @Test
    void shouldReturnAllPersonTest(){
        List<Medicalrecord> result = medicalrecordRepository.getAll();
        
        assertEquals(5, result.size());
        
    }
    
    @Test
    void shouldReturnAPersonWithFirstnameAndLastnameTest(){
        Medicalrecord result = medicalrecordService.getByNames(namesDTO);
        
        assertTrue(result.getFirstName().equals(firstName) && result.getLastName().equals(lastName));
        assertEquals("09/06/2017", result.getBirthdate());
    }
    
    @Test
    void shouldSaveANewPersonTest(){
        Medicalrecord toSave = new Medicalrecord("firstName", "lastName", "25/07/2022", new ArrayList<>(), new ArrayList<>());
        
        Medicalrecord result = medicalrecordService.save(toSave);
        
        int resultSize = medicalrecordService.getAll()
                .size();
        
        assertEquals("25/07/2022", result.getBirthdate());
        assertEquals(6, resultSize);
    }
    
    @Test
    void shouldUpdateAPersonTest(){
        Medicalrecord toUpdate = new Medicalrecord(null, null, null, new ArrayList<>(List.of("test", "test2")), new ArrayList<>(List.of("test")));
        
        Medicalrecord result = medicalrecordService.update(toUpdate, namesDTO);
        
        
        assertEquals(2, result.getMedications().size());
        assertEquals(1, result.getAllergies().size());
    }
    
    @Test
    void shouldDeleteAPersonTest(){
        medicalrecordService.deleteByNames(namesDTO);
        
       assertDoesNotThrow(() -> medicalrecordService.deleteByNames(namesDTO));
    }
}
