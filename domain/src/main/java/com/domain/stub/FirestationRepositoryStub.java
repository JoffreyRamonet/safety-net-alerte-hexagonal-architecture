package com.domain.stub;


import com.domain.ddd.Stub;
import com.domain.model.Firestation;
import com.domain.spi.FirestationSpi;
import com.domain.stub.test_impl.DataStub;

import java.util.List;

@Stub
public class FirestationRepositoryStub implements FirestationSpi {
    
    private final DataStub data = new DataStub();
    private final List<Firestation> firestations = data.firestations();
    
    @Override
    public List<Firestation> getAll() {
        return firestations;
    }
    
    @Override
    public List<Firestation> getByStation(String station) {
        return firestations.stream()
                .filter(firestation -> firestation.getStation()
                        .equals(station))
                .toList();
    }
    
    @Override
    public Firestation getByAddress(String address) {
        return firestations.stream()
                .filter(firestation -> firestation.getAddress()
                        .equals(address))
                .toList()
                .get(0);
    }
    
    @Override
    public Firestation save(Firestation firestation) {
        firestations.add(firestation);
        return firestation;
    }
    
    @Override
    public Firestation update(Firestation firestation, String address) {
        for(int i = 0; i < firestations.size(); i++) {
            if(firestations.get(i)
                    .getAddress()
                    .equals(address))
                firestations.set(i, firestation);
        }
        
        return firestation;
    }
    
    @Override
    public void delete(Firestation firestation) {
        firestations.removeIf(f -> f.getAddress()
                .equals(firestation.getAddress()) && f.getStation()
                .equals(firestation.getStation()));
    }
}
