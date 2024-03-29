package com.domain.service;


import com.domain.api.MedicalrecordsApi;
import com.domain.ddd.DomainService;
import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;
import com.domain.spi.MedicalrecordsSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class to perform Medicalrecord business treatments.
 * Implements MedicalrecordsApi to send responses to controllers in the infrastructure layer.
 *
 * @see MedicalrecordsApi
 * <p>
 * Use the MedicalrecordsSpi to get data from the infrastructure layer.
 * @see MedicalrecordsSpi
 * <p>
 * Annotated with @DomainService to be considered a Bean by the infrastructure layer.
 * @see DomainService
 */
@DomainService
public class MedicalrecordService implements MedicalrecordsApi {
    
    private final static Logger logger = LoggerFactory.getLogger(FirestationService.class);
    
    private final MedicalrecordsSpi medicalrecordsSpi;
    
    public MedicalrecordService(MedicalrecordsSpi medicalrecordsSpi) {
        this.medicalrecordsSpi = medicalrecordsSpi;
    }
    
    @Override
    public List<Medicalrecord> getAll() {
        return medicalrecordsSpi.getAll();
    }
    
    @Override
    public Medicalrecord getByNames(NamesDTO namesDTO) {
        logger.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        return medicalrecordsSpi.getByNames(namesDTO);
    }
    
    @Override
    public Medicalrecord save(Medicalrecord medicalrecord) {
        logger.debug(
                "The medicalrecord parsed: " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " " +
                        medicalrecord.getBirthdate() + " " + medicalrecord.getMedications() + " " +
                        medicalrecord.getAllergies());
        return medicalrecordsSpi.save(medicalrecord);
    }
    
    @Override
    public Medicalrecord update(Medicalrecord medicalrecord, NamesDTO namesDTO) {
        Medicalrecord medicalrecordToUpdate = medicalrecordsSpi.getByNames(namesDTO);
        
        if(medicalrecord.getMedications() != null)
            medicalrecordToUpdate.setMedications(medicalrecord.getMedications());
        if(medicalrecord.getAllergies() != null)
            medicalrecordToUpdate.setAllergies(medicalrecord.getAllergies());
        
        medicalrecordsSpi.update(medicalrecordToUpdate, namesDTO);
        
        logger.debug(
                "The medicalrecord parsed: " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " " +
                        medicalrecord.getBirthdate() + " " + medicalrecord.getMedications() + " " +
                        medicalrecord.getAllergies());
        logger.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        return medicalrecordToUpdate;
    }
    
    @Override
    public void deleteByNames(NamesDTO namesDTO) {
        logger.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        medicalrecordsSpi.deleteByNames(namesDTO);
    }
}
