package com.infrastructure.data_reader.data_list;

import com.domain.model.Medicalrecord;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * Class used to deserialize the jsonNode into a list of Medicalrecord.
 */
@Getter
@Setter
public class MedicalrecordsList {
    
    private List<Medicalrecord> medicalrecords = new ArrayList<>();

}
