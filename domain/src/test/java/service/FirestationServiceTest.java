package service;


import com.domain.dto.ListPersonStationNumberDTO;
import com.domain.model.Firestation;
import com.domain.service.AddressCalculatorService;
import com.domain.service.AgeCalculatorService;
import com.domain.service.FirestationService;
import com.domain.service.MedicalrecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.domain.stub.FirestationRepositoryStub;
import com.domain.stub.MedicalrecordRepositoryStub;
import com.domain.stub.PersonRepositoryStub;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class FirestationServiceTest {
    
    FirestationRepositoryStub firestationRepository;
    PersonRepositoryStub personRepository;
    MedicalrecordRepositoryStub medicalrecordRepository;
    MedicalrecordService medicalrecordService;
    AgeCalculatorService ageCalculatorService;
    AddressCalculatorService addressCalculatorService;
    FirestationService firestationService;

    @BeforeEach
    void setUp() {
        firestationRepository = new FirestationRepositoryStub();
        personRepository = new PersonRepositoryStub();
        medicalrecordRepository = new MedicalrecordRepositoryStub();
        medicalrecordService = new MedicalrecordService(medicalrecordRepository);
        ageCalculatorService = new AgeCalculatorService(medicalrecordService);
        addressCalculatorService = new AddressCalculatorService(firestationRepository, personRepository);
        firestationService =
                new FirestationService(firestationRepository, addressCalculatorService, ageCalculatorService);
    }
    
    @Test
    void shouldReturnAllFirestationTest() {
        
        List<Firestation> result = firestationService.getAll();
        
        assertEquals(3, result.size());
    }
    
    @Test
    void shouldReturnAListOfFirestationFromStationTest() {
        String station = "3";
        List<Firestation> result = firestationService.getByName(station);
        
        assertEquals(2, result.size());
    }
    
    @Test
    void shouldReturnAFirestationFromAddressTest() {
        String address = "1509 Culver St";
        
        Firestation result = firestationService.getByAddress(address);
        
        assertEquals("3", result.getStation());
    }
    
    @Test
    void shouldSaveANewFirestationTest() {
        Firestation firestation = new Firestation("address", "8");
        
        Firestation result = firestationService.save(firestation);
        
        assertEquals("address", result.getAddress());
        assertEquals("8", result.getStation());
    }
    
    @Test
    void shouldUpdateAFirestationTest(){
        String address = "1509 Culver St";
        
        Firestation firestation = new Firestation(null, "7");
        
        Firestation result = firestationService.update(firestation, address);
        
        assertEquals("7", result.getStation());
    }
    
    
    @Test
    void shouldDeleteAPersonTest() {
        String address = "29 15th St";
        String station = "2";
        Firestation firestation = new Firestation(address, station);
        
        firestationService.delete(firestation);
        assertDoesNotThrow(() -> firestationService.delete(firestation));
    }
    
    @Test
    void shouldReturnPersonsCoveredFromStation(){
        String station = "2";
        
        ListPersonStationNumberDTO result =
                firestationService.getPersonsCoveredByFirestationId(station);
        
        assertEquals(1, result.personStationNumberList().size());
    }
}
