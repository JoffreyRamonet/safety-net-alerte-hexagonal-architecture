package com.domain.spi;

import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;

import java.util.List;

/**
 * Interface to perform requests to the client in the infrastructure layer from the domain for Medicalrecord model.
 *
 * @see Medicalrecord
 * @see com.domain.service.MedicalrecordService
 * @see com.domain.service.SpecialsRequestsService
 */
public interface MedicalrecordsSpi {
    List<Medicalrecord> getAll();
    Medicalrecord getByNames(NamesDTO namesDTO);
    Medicalrecord save(Medicalrecord medicalrecord);
    Medicalrecord update(Medicalrecord medicalrecord, NamesDTO namesDTO);
    void deleteByNames(NamesDTO namesDTO);
    
    
}
