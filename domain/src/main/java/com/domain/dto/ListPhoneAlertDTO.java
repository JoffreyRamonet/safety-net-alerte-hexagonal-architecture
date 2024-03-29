package com.domain.dto;

import java.util.List;

/**
 * Used to store a list of PhoneAlertDTO .
 * @param phoneAlertList store a phone number.
 * @see com.domain.api.SpecialsRequestsApi
 * @see com.domain.service.SpecialsRequestsService
 * @see PhoneAlertDTO
 */
public record ListPhoneAlertDTO(List<PhoneAlertDTO> phoneAlertList) {


	
}
