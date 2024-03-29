package com.infrastructure.controller.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * Modify ListPersonsCoveredResponseDTO to give JsonProperty
 *
 * @see com.domain.dto.ListPersonsCoveredResponseDTO
 */
@Builder
@Getter
@Setter
public class ListPersonsCoveredResponseDto {
    
    @JsonProperty("PersonsCovered")
    List<ListPersonsCoveredDto> listPersonsCoveredList;
}
