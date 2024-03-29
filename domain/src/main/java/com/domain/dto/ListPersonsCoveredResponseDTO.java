package com.domain.dto;

import java.util.List;

/**
 * Used to store a list of ListPersonsCoveredDTO.
 *
 * @param listPersonsCoveredList contains a list of PersonAndMedicalrecordsDTO and their address.
 * @see ListPersonsCoveredDTO
 * @see PersonAndMedicalrecordsDTO
 * @see domain.api.SpecialsRequestsApi
 * @see domain.service.SpecialsRequestsService
 */
public record ListPersonsCoveredResponseDTO(List<ListPersonsCoveredDTO> listPersonsCoveredList) {

}
