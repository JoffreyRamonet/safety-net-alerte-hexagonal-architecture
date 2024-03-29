package com.domain.api;


import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;

import java.util.List;

/**
 * Interface to perform requests from controllers to the domain for the Medicalrecord model.
 *
 * @see Medicalrecord
 * @see com.domain.service.MedicalrecordService
 */
public interface MedicalrecordsApi {
    List<Medicalrecord> getAll();
    
    Medicalrecord getByNames(NamesDTO namesDTO);
    
    Medicalrecord save(Medicalrecord medicalrecord);
    
    Medicalrecord update(Medicalrecord medicalrecord, NamesDTO namesDTO);
    
    void deleteByNames(NamesDTO namesDTO);
}
