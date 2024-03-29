package com.infrastructure.controller.dto;

import com.domain.dto.ListChildAlertDTO;
import com.domain.dto.ListFirePersonDTO;
import com.domain.dto.ListPersonStationNumberDTO;
import com.domain.dto.ListPersonsCoveredDTO;
import com.domain.dto.ListPersonsCoveredResponseDTO;
import com.domain.dto.ListPersonsInfoDTO;

/**
 * Interface to map domain's Data Transfer Object into controller's Data Transfer Object.
 */
public interface DtoMapper {
    
    ListChildAlertDto mapper(ListChildAlertDTO listChildAlertDTO);
    
    ListFirePersonDto mapper(ListFirePersonDTO listFirePersonDTO);
    
    ListPersonsCoveredDto mapper(ListPersonsCoveredDTO listPersonsCoveredDTO);
    
    ListPersonsCoveredResponseDto mapper(ListPersonsCoveredResponseDTO listPersonsCoveredResponseDTO);
    
    ListPersonsInfoDto mapper(ListPersonsInfoDTO listPersonsInfoDTO);
    
    ListPersonStationNumberDto mapper(ListPersonStationNumberDTO listPersonStationNumberDTO);
}
