package com.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.domain.dto.ChildAlertDTO;
import com.domain.dto.NamesDTO;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Modify ListChildAlertDTO to give JsonProperties
 *
 * @see com.domain.dto.ListChildAlertDTO
 */
@Builder
@Getter
@Setter
public class ListChildAlertDto {
    
    @JsonProperty("Children")
    private List<ChildAlertDTO> childAlertList;
    @JsonProperty("Family")
    private List<NamesDTO> familyList;
}
