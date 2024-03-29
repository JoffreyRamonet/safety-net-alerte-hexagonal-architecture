package com.domain.dto;


import java.util.List;

/**
 * Used to store a list of PersonAndMedicalrecordsDTO and the station of the firestation who covered the list of person address.
 *
 * @param personFireAlertList
 * @param firestationNumber
 * @see PersonAndMedicalrecordsDTO
 * @see domain.api.SpecialsRequestsApi
 * @see domain.service.SpecialsRequestsService
 */
public record ListFirePersonDTO(List<PersonAndMedicalrecordsDTO> personFireAlertList, String firestationNumber) {

}
