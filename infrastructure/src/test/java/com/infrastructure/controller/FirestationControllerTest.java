package com.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.domain.model.Firestation;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = FirestationController.class)
@Import({TestDomainControllerConfiguration.class, DtoMapperService.class})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class FirestationControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @Order(1)
    void shouldReturnAllFirestationTest() throws Exception {
        mvc.perform(get("/api/v1/firestation"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(3))
                .andDo(print());
    }
    
    @Test
    @Order(2)
    void shouldReturnAListOfPersonCoveredByAStationTest() throws Exception {
        String station = "3";
        
        mvc.perform(get("/api/v1/firestation/firestation/{station}", station))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.Persons.size()").value(4))
                .andExpect(jsonPath("$.adult").value(2))
                .andExpect(jsonPath("$.adult").value(2))
                .andDo(print());
    }
    
    @Test
    @Order(3)
    void shouldReturnTheListOfFirestationFromStationTest() throws Exception {
        String station = "3";
        
        mvc.perform(get("/api/v1/firestation/{station}", station))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(2))
                .andDo(print());
    }
    
    @Test
    @Order(5)
    void shouldSaveANewFirestationTest() throws Exception {
        Firestation firestation = new Firestation("834 Binoc Ave", "9");
        
        mvc.perform(post("/api/v1/firestation/add").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    @Test
    @Order(6)
    void shouldUpdateAFirestationTest() throws Exception{
        String address = "834 Binoc Ave";
        Firestation firestation = new Firestation(null, "3");
        
        mvc.perform(patch("/api/v1/firestation/update/{address}", address).contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(firestation)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.station").value(3))
                .andDo(print());
    }
    
    @Test
    @Order(4)
    void shouldDeleteAFirestationTest() throws Exception{
        String address = "834 Binoc Ave";
        String station = "3";
        
        mvc.perform(delete("/api/v1/firestation/delete/{address}/{station}", address, station))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
