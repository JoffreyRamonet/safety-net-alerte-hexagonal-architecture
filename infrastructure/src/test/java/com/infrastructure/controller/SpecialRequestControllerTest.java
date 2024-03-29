package com.infrastructure.controller;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = SpecialsRequestsController.class)
@Import({TestDomainControllerConfiguration.class, DtoMapperService.class})
public class SpecialRequestControllerTest {
    
    @Autowired
    private MockMvc mvc;
 
    @Test
    void shouldReturnChildrenAndFamilyByAddressTest() throws Exception{
        String address = "1509 Culver St";
        
        mvc.perform(get("/api/v1/childAlert/{address}", address)).andExpect(status().isOk())
                .andExpect(jsonPath("$.Children.size()").value(2))
                .andExpect(jsonPath("$.Family.size()").value(2))
                .andDo(print());
    }
    
    @Test
    void shouldReturnThePhoneListCoveredByAFirestationNumberTest() throws Exception {
        String station = "3";
        
        mvc.perform(get("/api/v1/phoneAlert/{station}", station))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.phoneAlertList.size()").value(4))
                .andDo(print());
    }
    
    @Test
    void shouldReturnPersonsAndTheirMedicalrecordsAndTheFirestationFromAddressTest() throws Exception {
        String address = "1509 Culver St";
        
        mvc.perform(get("/api/v1/fire/{address}", address)).andExpect(status().isOk())
                .andExpect(jsonPath("$.Persons.size()").value(4))
                .andExpect(jsonPath("$.firestationNumber").value(3))
                .andDo(print());
    }
    
    @Test
    void shouldReturnPersonsAndTheirMedicalrecordsFromAFirestationListTest() throws Exception{
        String stations = "3,2";
        
        mvc.perform(get("/api/v1/flood/{station}", stations)).andExpect(status().isOk())
                .andExpect(jsonPath("$.PersonsCovered.size()").value(3))
                .andExpect(jsonPath("$.PersonsCovered[0].persons[0].firstName").value("John"))
                .andDo(print());
    }
    
    @Test
    void shouldReturnAPersonAndTheirFamilyTest() throws Exception{
        String firstName = "Roger";
        String lastName = "Boyd";
        
        mvc.perform(get("/api/v1/personInfo/{firstName}/{lastName}", firstName, lastName)).andExpect(status().isOk())
                .andExpect(jsonPath("$.Persons.size()").value(4))
                .andDo(print());
    }
    
    @Test
    void shouldReturnAllEmailOfACityTest() throws Exception{
        String city = "Culver";
        
        mvc.perform(get("/api/v1/communityEmail/{city}", city)).andExpect(status().isOk())
                .andExpect(jsonPath("$.emailList.size()").value(5))
                .andDo(print());
    }
    
    
}
