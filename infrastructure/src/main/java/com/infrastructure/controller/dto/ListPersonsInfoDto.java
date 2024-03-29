package com.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.domain.dto.PersonInfosDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Modify ListPersonsInfoDTO to give JsonProperty
 *
 * @see com.domain.dto.ListPersonStationNumberDTO
 */
@Builder
@Getter
@Setter
public class ListPersonsInfoDto {
    
    @JsonProperty("Persons")
    List<PersonInfosDTO> personsInfoList;
}
