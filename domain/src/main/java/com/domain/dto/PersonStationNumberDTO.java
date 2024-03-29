package com.domain.dto;

/**
 * Used to store partial information from a Person.
 *
 * @param firstName
 * @param lastName
 * @param address
 * @param phone
 * @see ListPersonStationNumberDTO
 * @see com.domain.service.FirestationService
 */
public record PersonStationNumberDTO(String firstName, String lastName, String address, String phone) {


}
