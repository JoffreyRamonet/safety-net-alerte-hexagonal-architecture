package com.infrastructure.controller;

import com.infrastructure.controller.dto.DtoMapper;
import com.infrastructure.controller.dto.ListPersonStationNumberDto;
import com.domain.api.FirestationApi;
import com.domain.model.Firestation;
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
 * Class to perform request on the uri /api/v1/firestation.
 * <p>
 * Call firestationApi to get data to display.
 *
 * @see FirestationApi
 */
@Slf4j
@RestController
@RequestMapping("/api/v1/firestation")
public class FirestationController {
    
    
    private final FirestationApi firestationApi;
    private final DtoMapper dtoMapper;
    
    public FirestationController(FirestationApi firestationApi, DtoMapper dtoMapper) {
        this.firestationApi = firestationApi;
        this.dtoMapper = dtoMapper;
    }
    
    
    @GetMapping
    public List<Firestation> getAll() {
        return firestationApi.getAll();
    }
    
    @GetMapping("/firestation/{station}")
    public ListPersonStationNumberDto findPersonsCoveredByFirestationId(
            @PathVariable
            final String station) {
        log.debug("Variable parsed: " + station);
        return dtoMapper.mapper(firestationApi.getPersonsCoveredByFirestationId(station));
    }
    
    @GetMapping("{station}")
    public List<Firestation> findBy(
            @PathVariable
            final String station) {
        log.debug("Variable parsed: " + station);
        return firestationApi.getByName(station);
    }
    
    @PostMapping("/add")
    public Firestation save(
            @RequestBody Firestation firestation) {
        log.debug("Firestation parsed: " + firestation.getAddress() + " " + firestation.getStation());
        return firestationApi.save(firestation);
    }
    
    @DeleteMapping("/delete/{address}/{station}")
    public void deleteBy(
            @PathVariable
            final String address,
            @PathVariable
            final String station) {
        log.debug("Variable parsed: " + address + station);
        firestationApi.delete(new Firestation(address, station));
    }
    
    @PatchMapping("/update/{address}")
    public Firestation update(
            @RequestBody Firestation firestation,
            @PathVariable
            final String address) {
        log.debug("Firestation parsed: " + firestation.getAddress() + " " + firestation.getStation());
        log.debug("Variable parsed: " + address);
        return firestationApi.update(firestation, address);
    }
}
