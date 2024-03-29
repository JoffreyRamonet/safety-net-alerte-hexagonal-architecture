package com.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.domain.dto.PersonAndMedicalrecordsDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Modify ListFirePersonDTO to give JsonProperty
 *
 * @see com.domain.dto.ListFirePersonDTO
 */
@Builder
@Getter
@Setter
public class ListFirePersonDto {
    
    @JsonProperty("Persons")
    List<PersonAndMedicalrecordsDTO> personFireAlertList;
    
    String firestationNumber;
}
