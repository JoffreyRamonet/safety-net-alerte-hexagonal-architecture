package com.infrastructure.data_reader.repository;

import com.infrastructure.data_reader.JsonReader;
import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;
import com.domain.spi.MedicalrecordsSpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository of the model Medicalrecord.
 * Implement the MedicalrecordsSpi to give data from the JsonFile to the domain.
 * Annotated with @Primary cause of repositories of the JsonReader client are the default choice.
 *
 * @see Primary
 * @see MedicalrecordsSpi
 * @see JsonReader
 */
@Slf4j
@Repository
@Primary
public class MedicalrecordRepository implements MedicalrecordsSpi {
    /**
     * Call the JsonReader to create an InMemory list of Medicalrecord.
     */
    private final List<Medicalrecord> medicalrecords = JsonReader.medicalrecordsList();
    
    /**
     * cRud method - read all.
     *
     * @return a list of all Medicalrecord.
     */
    @Override
    public List<Medicalrecord> getAll() {
        return medicalrecords;
    }
    
    /**
     * cRud method - read  by namesDTO.
     *
     * @param namesDTO contains String firstName and String lastName.
     * @return a Medicalrecord with the firstName and the lastName of the NamesDto parsed.
     */
    @Override
    public Medicalrecord getByNames(NamesDTO namesDTO) {
        log.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        return medicalrecords.stream()
                .filter(medicalrecord -> medicalrecord.getFirstName()
                        .equals(namesDTO.firstName()) && medicalrecord.getLastName()
                        .equals(namesDTO.lastName()))
                .toList()
                .get(0);
    }
    
    /**
     * Crud method - create a new Medicalrecord.
     * CAUTION - the Medicalrecord is saved in memory and not writing in the JsonFile!
     *
     * @param medicalrecord Medicalrecord parsed to be saved.
     * @return the Medicalrecord saved.
     */
    @Override
    public Medicalrecord save(Medicalrecord medicalrecord) {
        log.debug(
                "The Medicalrecord parsed: " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " " +
                        medicalrecord.getBirthdate() + " " + medicalrecord.getMedications() + " " +
                        medicalrecord.getAllergies());
        medicalrecords.add(medicalrecord);
        return medicalrecord;
    }
    
    /**
     * crUd method - update by NamesDto.
     * CAUTION - the Medicalrecord is updated in memory and not updated in the JsonFile!
     *
     * @param medicalrecord Medicalrecord with attributes have to be modified.
     * @param namesDTO      contains String firstName and String lastName.
     * @return the Medicalrecord object modified.
     */
    @Override
    public Medicalrecord update(Medicalrecord medicalrecord, NamesDTO namesDTO) {
        for(int i = 0; i < medicalrecords.size(); i++) {
            if(medicalrecords.get(i)
                    .getFirstName()
                    .equals(namesDTO.firstName()) && medicalrecords.get(i)
                    .getLastName()
                    .equals(namesDTO.lastName()))
                medicalrecords.set(i, medicalrecord);
        }
        log.debug(
                "The Medicalrecord parsed: " + medicalrecord.getFirstName() + " " + medicalrecord.getLastName() + " " +
                        medicalrecord.getBirthdate() + " " + medicalrecord.getMedications() + " " +
                        medicalrecord.getAllergies());
        log.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        return medicalrecord;
    }
    
    /**
     * cruD delete by NamesDto.
     * CAUTION - the Medicalrecord is deleted in memory and not deleted in the JsonFile!
     *
     * @param namesDTO contains String firstName and String lastName.
     */
    @Override
    public void deleteByNames(NamesDTO namesDTO) {
        log.debug("The namesDTO parsed: " + namesDTO.firstName() + " " + namesDTO.lastName());
        medicalrecords.removeIf(medicalrecord -> medicalrecord.getFirstName()
                .equals(namesDTO.firstName()) && medicalrecord.getLastName()
                .equals(namesDTO.lastName()));
    }
}
