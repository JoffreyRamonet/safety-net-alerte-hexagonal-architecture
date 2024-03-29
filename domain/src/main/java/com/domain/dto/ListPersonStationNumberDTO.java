package com.domain.dto;


import java.util.List;

/**
 * Used to store a list of PersonStationNumberDTO and a count of minor and adult from the PersonStationNumberDTO list.
 *
 * @param personStationNumberList the list of PersonStationNumberDTO.
 * @param adult                   the count of adult.
 * @param child                   the count of minor.
 * @see PersonStationNumberDTO
 * @see com.domain.api.FirestationApi
 * @see com.domain.service.FirestationService
 */
public record ListPersonStationNumberDTO(List<PersonStationNumberDTO> personStationNumberList, int adult, int child) {


}
