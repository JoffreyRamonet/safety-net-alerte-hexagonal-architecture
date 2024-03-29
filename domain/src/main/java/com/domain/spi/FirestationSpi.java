package com.domain.spi;


import com.domain.model.Firestation;

import java.util.List;

/**
 * Interface to perform requests to the client in the infrastructure layer from the domain for Firestation model.
 *
 * @see Firestation
 * @see com.domain.service.PersonService
 * @see com.domain.service.SpecialsRequestsService
 */
public interface FirestationSpi {
    List<Firestation> getAll();
    
    List<Firestation> getByStation(String station);
    
    Firestation getByAddress(String address);
    
    Firestation save(Firestation firestation);
    
    Firestation update(Firestation firestation, String address);
    
    void delete(Firestation firestation);
}
