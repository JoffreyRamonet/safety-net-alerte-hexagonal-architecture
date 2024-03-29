package com.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.domain.dto.PersonStationNumberDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Modify ListPersonStationNumberDTO to give JsonProperty
 *
 * @see com.domain.dto.ListPersonStationNumberDTO
 */
@Builder
@Getter
@Setter
public class ListPersonStationNumberDto {
    
    
    @JsonProperty("Persons")
    private List<PersonStationNumberDTO> personStationNumberList = new ArrayList<>();
    
    private int adult;
    private int child;
}
