package com.infrastructure.controller;


import com.infrastructure.controller.dto.DtoMapper;
import com.infrastructure.controller.dto.ListChildAlertDto;
import com.infrastructure.controller.dto.ListFirePersonDto;
import com.infrastructure.controller.dto.ListPersonsCoveredResponseDto;
import com.infrastructure.controller.dto.ListPersonsInfoDto;
import com.domain.api.SpecialsRequestsApi;
import com.domain.dto.ListEmailDTO;
import com.domain.dto.ListPhoneAlertDTO;
import com.domain.dto.NamesDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Class to perform special requests on the uri /api/v1.
 * <p>
 * Call specialsRequestsApi to get data to display.
 * Call DtoMapper to map domain DTO into infrastructure Dto before displaying data.
 *
 * @see SpecialsRequestsApi
 * @see DtoMapper
 * @see com.infrastructure.controller.dto
 */
@Slf4j
@RestController
@RequestMapping("/api/v1")
public class SpecialsRequestsController {
    
    private final SpecialsRequestsApi specialsRequestsApi;
    private final DtoMapper dtoMapper;
    
    public SpecialsRequestsController(SpecialsRequestsApi specialsRequestsApi, DtoMapper dtoMapper) {
        this.specialsRequestsApi = specialsRequestsApi;
        this.dtoMapper = dtoMapper;
    }
    
    @GetMapping("/childAlert/{address}")
    public ListChildAlertDto findChildrenAndFamilyAt(
            @PathVariable
            final String address) {
        log.debug("Variable parsed: " + address);
        return dtoMapper.mapper(specialsRequestsApi.getChildrenAndFamilyAtAddress(address));
    }
    
    @GetMapping("/phoneAlert/{station}")
    public ListPhoneAlertDTO findPhoneOfAllPersonsCoveredByFirestationNumber(
            @PathVariable
            final String station) {
        log.debug("Variable parsed: " + station);
        return specialsRequestsApi.getPhoneOfAllPersonsCoveredByFirestationNumber(station);
    }
    
    @GetMapping("/fire/{address}")
    public ListFirePersonDto findPersonsAndTheirMedicalrecordsFromAddressAndTheirFirestation(
            @PathVariable
            final String address) {
        log.debug("Variable parsed: " + address);
        return dtoMapper.mapper(
                specialsRequestsApi.getPersonsAndTheirMedicalrecordsFromAddressAndTheirFirestation(address));
    }
    
    @GetMapping("/flood/{station}")
    public ListPersonsCoveredResponseDto findPersonsAndTheirMedicalrecordsFromAFirestationList(
            @PathVariable
            final List<String> station) {
        log.debug("Variable parsed: " + station);
        return dtoMapper.mapper(specialsRequestsApi.getPersonsAndTheirMedicalrecordsFromAFirestationList(station));
    }
    
    @GetMapping("/personInfo/{firstName}/{lastName}")
    public ListPersonsInfoDto findPersonAndTheirFamily(
            @PathVariable
            final String firstName,
            @PathVariable
            final String lastName) {
        log.debug("Variable parsed: " + firstName + " " + lastName);
        return dtoMapper.mapper(specialsRequestsApi.getPersonAndTheirFamily(new NamesDTO(firstName, lastName)));
    }
    
    @GetMapping("/communityEmail/{city}")
    public ListEmailDTO findAllMailOfTheCity(
            @PathVariable
            final String city) {
        log.debug("Variable parsed: " + city);
        return specialsRequestsApi.getAllMailOfTheCity(city);
    }
}
