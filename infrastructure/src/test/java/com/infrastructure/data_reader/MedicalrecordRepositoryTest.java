package com.infrastructure.data_reader;

import com.infrastructure.data_reader.repository.MedicalrecordRepository;
import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;


public class MedicalrecordRepositoryTest {
    
    MedicalrecordRepository medicalrecordRepository;
    
    private final String firstName = "Roger";
    private final String lastName = "Boyd";
    private final NamesDTO namesDTO = new NamesDTO(firstName, lastName);
    
    @BeforeEach
    void setUp() {
        JsonReader.setFilepath("/dataStub.json");
        medicalrecordRepository = new MedicalrecordRepository();
    }
    
    @Test
    void shouldReturnAllMedicalrecordTest(){
        List<Medicalrecord> result = medicalrecordRepository.getAll();
        
        assertEquals(8, result.size());
    }
    
    @Test
    void shouldReturnAMedicalrecordWithNamesTest(){
        Medicalrecord result = medicalrecordRepository.getByNames(namesDTO);
        
        assertEquals("09/06/2017", result.getBirthdate());
    }
    
    @Test
    void shouldSaveANewMedicalrecordTest(){
        Medicalrecord medicalrecord = new Medicalrecord("firstName", "lastName", "09/08/1993", new ArrayList<>(), new ArrayList<>());
        
        Medicalrecord result = medicalrecordRepository.save(medicalrecord);
        
        assertEquals(medicalrecord, result);
    }
    @Test
    void shouldUpdateAMedicarecordTest(){
        Medicalrecord medicalrecord = new Medicalrecord("firstName", "lastName", "09/08/1993", new ArrayList<>(List.of("test1", "test2")), new ArrayList<>(List.of("test1", "test2")));
        
        Medicalrecord result = medicalrecordRepository.update(medicalrecord, namesDTO);
        
        assertEquals(medicalrecord, result);
    }
    
    @Test
    void shouldDeleteAMedicalrecordTest(){
        medicalrecordRepository.deleteByNames(namesDTO);
        
        List<Medicalrecord> result = medicalrecordRepository.getAll();
        
        assertEquals(7, result.size());
        
    }
}
