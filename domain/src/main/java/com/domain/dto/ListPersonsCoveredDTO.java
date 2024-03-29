package com.domain.dto;


import java.util.List;

public record ListPersonsCoveredDTO(String address, List<PersonAndMedicalrecordsDTO> personAndMedicalrecordsList ) {
	
}
