package com.infrastructure.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.domain.model.Person;
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

@WebMvcTest(controllers = PersonController.class)
@Import(TestDomainControllerConfiguration.class)
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PersonControllerTest {
    
    @Autowired
    private MockMvc mvc;
    
    @Autowired
    private ObjectMapper objectMapper;
    
    @Order(1)
    @Test
    void shouldReturnAllPersonTest() throws Exception {
        mvc.perform(get("/api/v1/person"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.size()").value(5))
                .andDo(print());
    }
    
    @Order(2)
    @Test
    void shouldReturnAPersonWithNamesTest() throws Exception {
        String firstName = "Roger";
        String lastName = "Boyd";
        
        mvc.perform(get("/api/v1/person/{firstName}/{lastName}", firstName, lastName))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.lastName").value(lastName))
                .andExpect(jsonPath("$.email").value("jaboyd@email.com"))
                .andDo(print());
    }
    
    @Order(4)
    @Test
    void shouldSaveANewPersonTest() throws Exception {
        Person person =
                new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "jaboyd@email.com");
        
        mvc.perform(post("/api/v1/person/add").contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andDo(print());
    }
    
    @Order(5)
    @Test
    void shouldUpdateAPersonTest() throws Exception {
        String firstName = "Jonanathan";
        String lastName = "Marrack";
        
        Person person = new Person(null, null, null, null, null, null, "email");
        
        mvc.perform(patch("/api/v1/person/update/{firstName}/{lastName}", firstName, lastName).contentType(
                                MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(person)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value(firstName))
                .andExpect(jsonPath("$.email").value("email"))
                .andDo(print());
    }
    
    @Order(3)
    @Test
    void shouldDeleteAPersonTest() throws Exception {
        String firstName = "Roger";
        String lastName = "Boyd";
        
        mvc.perform(delete("/api/v1/person/delete/{firstName}/{lastName}", firstName, lastName))
                .andExpect(status().isOk())
                .andDo(print());
    }
}
