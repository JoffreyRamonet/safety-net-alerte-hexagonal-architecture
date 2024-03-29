package com.domain.dto;


import java.util.List;

/**
 * Used to store a list of EmailDTO.
 *
 * @param emailList
 * @see EmailDTO
 * @see domain.api.SpecialsRequestsApi
 * @see domain.service.SpecialsRequestsService
 */

public record ListEmailDTO(List<EmailDTO> emailList) {

}
