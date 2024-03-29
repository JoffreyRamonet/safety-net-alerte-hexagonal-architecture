package com.infrastructure.controller;

import com.domain.api.MedicalrecordsApi;
import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class to perform request on the uri /api/v1/medicalrecord.
 * <p>
 * Call medicalrecordsApi to get data to display.
 *
 * @see MedicalrecordsApi
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/medicalrecord")
public class MedicalrecordController {
    
    private final MedicalrecordsApi medicalrecordsApi;
    
    public MedicalrecordController(MedicalrecordsApi medicalrecordsApi) {
        this.medicalrecordsApi = medicalrecordsApi;
    }
    
    @GetMapping
    public List<Medicalrecord> getAll() {
        return medicalrecordsApi.getAll();
    }
    
    @GetMapping("/{firstName}/{lastName}")
    public Medicalrecord findBy(
            @PathVariable
            final String firstName,
            @PathVariable
            final String lastName) {
        log.debug("Variable parsed: " + firstName + " " + lastName);
        return medicalrecordsApi.getByNames(new NamesDTO(firstName, lastName));
    }
    
    @PostMapping("/add")
    public Medicalrecord save(
            @RequestBody Medicalrecord medicalrecord) {
        log.debug(
                "The Medicalrecord parsed: " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " " +
                        medicalrecord.getBirthdate() + " " + medicalrecord.getMedications() + " " +
                        medicalrecord.getAllergies());
        return medicalrecordsApi.save(medicalrecord);
    }
    
    @PatchMapping("/update/{firstName}/{lastName}")
    public Medicalrecord update(
            @RequestBody Medicalrecord medicalrecord,
            @PathVariable
            final String firstName,
            @PathVariable
            final String lastName) {
        log.debug(
                "The Medicalrecord parsed: " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " " +
                        medicalrecord.getBirthdate() + " " + medicalrecord.getMedications() + " " +
                        medicalrecord.getAllergies());
        log.debug("Variable parsed: " + firstName + " " + lastName);
        return medicalrecordsApi.update(medicalrecord, new NamesDTO(firstName, lastName));
    }
    
    @DeleteMapping("/delete/{firstName}/{lastName}")
    public void deleteBy(
            @PathVariable
            final String firstName,
            @PathVariable
            final String lastName) {
        log.debug("Variable parsed: " + firstName + " " + lastName);
        medicalrecordsApi.deleteByNames(new NamesDTO(firstName, lastName));
    }
}
