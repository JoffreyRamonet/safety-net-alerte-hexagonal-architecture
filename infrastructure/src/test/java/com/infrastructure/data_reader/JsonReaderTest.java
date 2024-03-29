package com.infrastructure.data_reader;


import com.domain.model.Firestation;
import com.domain.model.Medicalrecord;
import com.domain.model.Person;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
@SpringBootTest
public class JsonReaderTest {

    @BeforeEach
    void setUp(){
        JsonReader.setFilepath("/dataStub.json");
    }
    
    @Test
    void shouldReturnTheListOfPersonFromAJsonFileTest(){
        List<Person> result = JsonReader.personsList();
        
        assertEquals(8, result.size());
    }
    
    @Test
    void shouldReturnTheListOfMedicalrecordFromAJsonFileTest(){
        List<Medicalrecord> result = JsonReader.medicalrecordsList();
        
        assertEquals(8, result.size());
    }
    
    @Test
    void shouldReturnTheListOfFirestationFromAJsonFileTest(){
        List<Firestation> result = JsonReader.firestationsList();
        
        assertEquals(4, result.size());
    }
}
