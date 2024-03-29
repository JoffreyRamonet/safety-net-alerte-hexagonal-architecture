package com.infrastructure.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.domain.model.Medicalrecord;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(controllers = MedicalrecordController.class)
@Import(TestDomainControllerConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class MedicalrecordControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Test
    @Order(1)
    void shouldReturnAllMedicalrecordTest() throws Exception {
        mvc.perform(get("/api/v1/medicalrecord"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(5))
                .andDo(print());
    }
    
    @Test
    @Order(2)
    void shouldReturnAMedicalrecordFromNamesTest() throws Exception {
        String firstName = "Roger";
        String lastName = "Boyd";
        mvc.perform(get("/api/v1/medicalrecord/{firstName}/{lastName}", firstName, lastName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.birthdate").value("09/06/2017"))
                .andDo(print());
    }
    
    @Test
    @Order(4)
    void shouldSaveANewMedicalrecordTest() throws Exception {
        Medicalrecord medicalrecord =
                new Medicalrecord("Jonanathan", "Marrack", "01/03/1989", new ArrayList<>(List.of("test")), new ArrayList<>());
        
        mvc.perform(post("/api/v1/medicalrecord/add").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalrecord)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    @Test
    @Order(5)
    void shouldUpdateAMedicalrecordTest() throws Exception {
        String firstName = "Jonanathan";
        String lastName = "Marrack";
        Medicalrecord medicalrecord = new Medicalrecord(null, null, null, new ArrayList<>(), null);
        
        mvc.perform(patch("/api/v1/medicalrecord/update/{firstName}/{lastName}", firstName, lastName).contentType(
                                MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(medicalrecord)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.medications.size()").value(0))
                .andDo(print());
    }
    
    @Test
    @Order(3)
    void shouldDeleteAMedicalrecordTest() throws Exception {
        String firstName = "Jonanathan";
        String lastName = "Marrack";
        
        mvc.perform(delete("/api/v1/medicalrecord/delete/{firstName}/{lastName}", firstName, lastName))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
