package service;


import com.domain.dto.ListChildAlertDTO;
import com.domain.dto.ListEmailDTO;
import com.domain.dto.ListFirePersonDTO;
import com.domain.dto.ListPersonsCoveredResponseDTO;
import com.domain.dto.ListPersonsInfoDTO;
import com.domain.dto.ListPhoneAlertDTO;
import com.domain.dto.NamesDTO;
import com.domain.service.AgeCalculatorService;
import com.domain.service.MedicalrecordService;
import com.domain.service.SpecialsRequestsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import com.domain.stub.FirestationRepositoryStub;
import com.domain.stub.MedicalrecordRepositoryStub;
import com.domain.stub.PersonRepositoryStub;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SpecialdsRequestServiceTest {
    
    PersonRepositoryStub personRepository;
    FirestationRepositoryStub firestationRepository;
    MedicalrecordRepositoryStub medicalrecordRepository;
    MedicalrecordService medicalrecordService;
    AgeCalculatorService ageCalculatorService;
    SpecialsRequestsService specialsRequestsService;
    
    @BeforeEach
    void setUp() {
        personRepository = new PersonRepositoryStub();
        firestationRepository = new FirestationRepositoryStub();
        medicalrecordRepository = new MedicalrecordRepositoryStub();
        medicalrecordService = new MedicalrecordService(medicalrecordRepository);
        ageCalculatorService = new AgeCalculatorService(medicalrecordService);
        specialsRequestsService = new SpecialsRequestsService(personRepository, firestationRepository, medicalrecordRepository, ageCalculatorService);
    }
    
    @Test
    void shouldReturnAListChildAlertDTOFromAnAddressTest(){
        
        String address = "1509 Culver St";
        
        ListChildAlertDTO result = specialsRequestsService.getChildrenAndFamilyAtAddress(address);
        
        assertEquals(2, result.childAlertList().size());
        assertEquals(2, result.familyList().size());
    }
    
    @Test
    void shouldReturnAListOfPhoneNumberUnderAFirestationRangeTest(){
        
        String station = "3";
        
        ListPhoneAlertDTO result =
                specialsRequestsService.getPhoneOfAllPersonsCoveredByFirestationNumber(station);
        
        assertEquals(4, result.phoneAlertList().size());
    }
    
    @Test
    void shouldReturnPersonsWithMedicalRecordsWithTheFirestationCoveredFromAddressTest(){
        
        String address = "1509 Culver St";
        
        ListFirePersonDTO result =
                specialsRequestsService.getPersonsAndTheirMedicalrecordsFromAddressAndTheirFirestation(address);
    
        assertEquals(4, result.personFireAlertList().size());
        assertEquals("3", result.firestationNumber());
    }
    
    @Test
    void shouldReturnPersonsWithMedicalRecordsFromAFirestationListTest(){
        List<String> stations = new ArrayList<>(List.of("3", "2"));
        
        ListPersonsCoveredResponseDTO result =
                specialsRequestsService.getPersonsAndTheirMedicalrecordsFromAFirestationList(stations);
    
    assertEquals(3, result.listPersonsCoveredList().size());
    }
    
    @Test
    void shouldReturnPersonsAndTheirFamilyTest(){
    String firstName = "Roger";
    String lastName = "Boyd";
        NamesDTO namesDTO = new NamesDTO(firstName, lastName);
        
        ListPersonsInfoDTO result = specialsRequestsService.getPersonAndTheirFamily(namesDTO);
        
        assertEquals(4, result.personsInfoList().size());
    }
    
    @Test
    void shouldReturnAllMailOfTheCityTest(){
        String city = "Culver";
        
        ListEmailDTO result = specialsRequestsService.getAllMailOfTheCity(city);
        
        assertEquals(5, result.emailList().size());
    }
}
