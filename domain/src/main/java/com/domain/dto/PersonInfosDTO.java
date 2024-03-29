package com.domain.dto;

/**
 * Used to store partial information from a Person and add his age and his Medicalrecords.
 * @param firstName
 * @param lastName
 * @param address
 * @param age
 * @param email
 * @param medicalrecord
 * @see com.domain.api.SpecialsRequestsApi
 * @see com.domain.service.SpecialsRequestsService
 * @see ListPersonsInfoDTO
 * @see MedicalrecordDTO
 */
public record PersonInfosDTO(String firstName, String lastName, String address, int age, String email,
                             MedicalrecordDTO medicalrecord) {
    
    
}
