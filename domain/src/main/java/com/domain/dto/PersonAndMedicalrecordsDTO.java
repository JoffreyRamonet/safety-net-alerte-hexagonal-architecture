package com.domain.dto;

/**
 * Used to store partial information of a person, his age and his Medicalrecord.
 *
 * @param firstName
 * @param lastName
 * @param phone
 * @param age
 * @param medicalrecord
 * @see com.domain.api.SpecialsRequestsApi
 * @see com.domain.service.SpecialsRequestsService
 * @see ListFirePersonDTO
 * @see ListPersonsCoveredDTO
 * @see ListPersonsCoveredResponseDTO
 * @see MedicalrecordDTO
 */
public record PersonAndMedicalrecordsDTO(String firstName, String lastName, String phone, int age,
                                         MedicalrecordDTO medicalrecord) {
    
    
}
