package com.domain.dto;

/**
 * Used to store the firstName, lastName and the age of a Child.
 *
 * @param firstName
 * @param lastName
 * @param age
 * @see com.domain.api.SpecialsRequestsApi
 * @see com.domain.service.SpecialsRequestsService
 * @see ListChildAlertDTO
 */
public record ChildAlertDTO(String firstName, String lastName, int age) {

}
