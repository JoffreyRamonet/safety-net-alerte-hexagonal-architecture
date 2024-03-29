package com.domain.dto;

import java.util.List;

/**
 * Used to store medications and allergies of a Person.
 *
 * @param medications
 * @param allergies
 * @see com.domain.service.SpecialsRequestsService
 * @see PersonInfosDTO
 * @see PersonAndMedicalrecordsDTO
 */
public record MedicalrecordDTO(List<String> medications, List<String> allergies) {


}
