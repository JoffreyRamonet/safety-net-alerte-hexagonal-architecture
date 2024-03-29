package com.domain.dto;

import java.util.List;

/**
 * Used to store a list of ChildAlertDTO and a list of NamesDTO of their family.
 *
 * @param childAlertList the list of ChildAlertDTO.
 * @param familyList     the list of NamesDTO.
 * @see domain.api.SpecialsRequestsApi
 * @see domain.service.SpecialsRequestsService
 */
public record ListChildAlertDTO(List<ChildAlertDTO> childAlertList, List<NamesDTO> familyList) {

}
