package com.infrastructure.controller;

import com.infrastructure.controller.dto.DtoMapper;
import com.infrastructure.controller.dto.ListChildAlertDto;
import com.infrastructure.controller.dto.ListFirePersonDto;
import com.infrastructure.controller.dto.ListPersonStationNumberDto;
import com.infrastructure.controller.dto.ListPersonsCoveredDto;
import com.infrastructure.controller.dto.ListPersonsCoveredResponseDto;
import com.infrastructure.controller.dto.ListPersonsInfoDto;
import com.domain.dto.ListChildAlertDTO;
import com.domain.dto.ListFirePersonDTO;
import com.domain.dto.ListPersonStationNumberDTO;
import com.domain.dto.ListPersonsCoveredDTO;
import com.domain.dto.ListPersonsCoveredResponseDTO;
import com.domain.dto.ListPersonsInfoDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to map domain's Data Transfer Object into controller's Data Transfer Object.
 * <p>
 * Give DTOs jackson annotations.
 */
@Service
public class DtoMapperService implements DtoMapper {
    
    @Override
    public ListChildAlertDto mapper(ListChildAlertDTO listChildAlertDTO) {
        return ListChildAlertDto.builder()
                .childAlertList(listChildAlertDTO.childAlertList())
                .familyList(listChildAlertDTO.familyList())
                .build();
    }
    
    @Override
    public ListFirePersonDto mapper(ListFirePersonDTO listFirePersonDTO) {
        return ListFirePersonDto.builder()
                .personFireAlertList(listFirePersonDTO.personFireAlertList())
                .firestationNumber(listFirePersonDTO.firestationNumber())
                .build();
    }
    
    @Override
    public ListPersonsCoveredDto mapper(ListPersonsCoveredDTO listPersonsCoveredDTO) {
        return ListPersonsCoveredDto.builder()
                .address(listPersonsCoveredDTO.address())
                .personAndMedicalrecordsList(listPersonsCoveredDTO.personAndMedicalrecordsList())
                .build();
    }
    
    @Override
    public ListPersonsCoveredResponseDto mapper(ListPersonsCoveredResponseDTO listPersonsCoveredResponseDTO) {
        List<ListPersonsCoveredDTO> listPersonsCoveredDTOS = listPersonsCoveredResponseDTO.listPersonsCoveredList();
        List<ListPersonsCoveredDto> listMapper = new ArrayList<>();
        
        for(ListPersonsCoveredDTO listPersonsCoveredDTO : listPersonsCoveredDTOS) {
            listMapper.add(mapper(listPersonsCoveredDTO));
        }
        
        return ListPersonsCoveredResponseDto.builder()
                .listPersonsCoveredList(listMapper)
                .build();
    }
    
    @Override
    public ListPersonsInfoDto mapper(ListPersonsInfoDTO listPersonsInfoDTO) {
        return ListPersonsInfoDto.builder()
                .personsInfoList(listPersonsInfoDTO.personsInfoList())
                .build();
    }
    
    @Override
    public ListPersonStationNumberDto mapper(ListPersonStationNumberDTO listPersonStationNumberDTO) {
        return ListPersonStationNumberDto.builder()
                .personStationNumberList(listPersonStationNumberDTO.personStationNumberList())
                .adult(listPersonStationNumberDTO.adult())
                .child(listPersonStationNumberDTO.child())
                .build();
    }
}
