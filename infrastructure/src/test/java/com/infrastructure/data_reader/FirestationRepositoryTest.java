package com.infrastructure.data_reader;

import com.infrastructure.data_reader.repository.FirestationRepository;
import com.domain.model.Firestation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirestationRepositoryTest {
    
    FirestationRepository firestationRepository;
    
    @BeforeEach
    void setUp() {
        JsonReader.setFilepath("/dataStub.json");
        firestationRepository = new FirestationRepository();
    }
    
    @Test
    void shouldReturnAllFirestationTest() {
        List<Firestation> result = firestationRepository.getAll();
        
        assertEquals(4, result.size());
    }
    
    @Test
    void shouldReturnAListOfFirestationFromAStationTest() {
        String station = "3";
        
        List<Firestation> restult = firestationRepository.getByStation(station);
        
        assertEquals(2, restult.size());
    }
    
    @Test
    void shouldReturnAFirestationFromAnAddressTest() {
        String address = "29 15th St";
        
        Firestation result = firestationRepository.getByAddress(address);
        
        assertEquals("2", result.getStation());
    }
    
    @Test
    void shouldSaveANewFirestationTest() {
        Firestation firestation = new Firestation("address", "9");
        
        firestationRepository.save(firestation);
        
        List<Firestation> result = firestationRepository.getAll();
        
        assertEquals(5, result.size());
    }
    
    @Test
    void shouldUpdateAFirestation() {
        Firestation firestation = new Firestation("newAddress", "newStation");
        
        Firestation result = firestationRepository.update(firestation, "1509 Culver St");
        
        assertEquals(firestation, result);
    }
    
    @Test
    void shouldDeleteAFirestation() {
        Firestation firestation = new Firestation("1509 Culver St", "3");
        
        firestationRepository.delete(firestation);
        
        List<Firestation> result = firestationRepository.getAll();
        
        assertEquals(3, result.size());
    }
}
