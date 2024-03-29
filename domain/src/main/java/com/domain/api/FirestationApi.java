package com.domain.api;


import com.domain.dto.ListPersonStationNumberDTO;
import com.domain.model.Firestation;

import java.util.List;

/**
 * Interface to perform requests from controllers to the domain for Firestation model.
 *
 * @see Firestation
 * @see com.domain.service.PersonService
 */
public interface FirestationApi {
    List<Firestation> getAll();
    
    List<Firestation> getByName(String station);
    
    Firestation getByAddress(String address);
    
    Firestation save(Firestation firestation);
    
    Firestation update(Firestation firestation, String address);
    
    void delete(Firestation firestation);
    
    /**
     * Return all Person living in an address covered by the firestation number parsed.
     *
     * @param station the number of the firestation, allows the firestation to be retrieved.
     * @return a ListPersonStationNumberDTO
     * @see ListPersonStationNumberDTO
     */
    ListPersonStationNumberDTO getPersonsCoveredByFirestationId(String station);
}
