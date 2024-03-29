package com.domain.dto;

/**
 * Used to found a Person or a Medicalrecord with a firstName and a lastName.
 * @param firstName
 * @param lastName
 *
 * @see com.domain.api.PersonApi
 * @see com.domain.api.MedicalrecordsApi
 * @see com.domain.api.SpecialsRequestsApi
 * @see com.domain.spi.PersonSpi
 * @see com.domain.spi.MedicalrecordsSpi
 */
public record NamesDTO(String firstName, String lastName) {
}
