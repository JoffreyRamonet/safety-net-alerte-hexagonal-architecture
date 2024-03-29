package com.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.domain.dto.PersonAndMedicalrecordsDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Modify ListPersonsCoveredDTO to give JsonProperty
 *
 * @see com.domain.dto.ListPersonsCoveredDTO
 */
@Builder
@Getter
@Setter
public class ListPersonsCoveredDto {
    
    @JsonProperty("persons")
    List<PersonAndMedicalrecordsDTO> personAndMedicalrecordsList;
    
    String address;
}
