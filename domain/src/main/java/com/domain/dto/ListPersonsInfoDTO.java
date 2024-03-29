package com.domain.dto;

import java.util.List;

/**
 * Used to store a list of PersonInfoDTO.
 *
 * @param personsInfoList contains information of a Person, their age and medicalrecord.
 * @see com.domain.api.SpecialsRequestsApi
 * @see com.domain.service.SpecialsRequestsService
 */
public record ListPersonsInfoDTO(List<PersonInfosDTO> personsInfoList) {

}
