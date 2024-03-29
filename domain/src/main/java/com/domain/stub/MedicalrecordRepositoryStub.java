package com.domain.stub;


import com.domain.ddd.Stub;
import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;
import com.domain.spi.MedicalrecordsSpi;
import com.domain.stub.test_impl.DataStub;

import java.util.List;

@Stub
public class MedicalrecordRepositoryStub implements MedicalrecordsSpi {
    private final DataStub data = new DataStub();
    private final List<Medicalrecord> medicalrecords = data.medicalrecords();

    @Override
    public List<Medicalrecord> getAll() {
        return medicalrecords;
    }
    
    @Override
    public Medicalrecord getByNames(NamesDTO namesDTO) {
        return medicalrecords.stream()
                .filter(medicalrecord -> medicalrecord.getFirstName()
                        .equals(namesDTO.firstName()) && medicalrecord.getLastName()
                        .equals(namesDTO.lastName()))
                .toList()
                .get(0);
    }
    
    @Override
    public Medicalrecord save(Medicalrecord medicalrecord) {
        medicalrecords.add(medicalrecord);
        return medicalrecord;
    }
    
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
        
        return medicalrecord;
    }
    
    @Override
    public void deleteByNames(NamesDTO namesDTO) {
        medicalrecords.removeIf(medicalrecord -> medicalrecord.getFirstName()
                .equals(namesDTO.firstName()) && medicalrecord.getLastName()
                .equals(namesDTO.lastName()));
    }
}
