package com.domain.service;

import com.domain.api.FirestationApi;
import com.domain.ddd.DomainService;
import com.domain.dto.ListPersonStationNumberDTO;
import com.domain.dto.PersonStationNumberDTO;
import com.domain.model.Firestation;
import com.domain.model.Person;
import com.domain.spi.FirestationSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to perform Firestation business treatments.
 * Implements FirestationApi to send responses to controllers in the infrastructure layer.
 *
 * @see FirestationApi
 * <p>
 * Use the FirestationSpi to get data from the infrastructure layer.
 * @see FirestationSpi
 * <p>
 * Annotated with @DomainService to be considered a Bean by the infrastructure layer.
 * @see DomainService
 */
@DomainService
public class FirestationService implements FirestationApi {
    
    private final static Logger logger = LoggerFactory.getLogger(FirestationService.class);
    private final FirestationSpi firestationSpi;
    private final AddressCalculatorService addressCalculatorService;
    private final AgeCalculatorService ageCalculatorService;
    
    public FirestationService(FirestationSpi firestationSpi, AddressCalculatorService addressCalculatorService,
                              AgeCalculatorService ageCalculatorService) {
        this.firestationSpi = firestationSpi;
        this.addressCalculatorService = addressCalculatorService;
        this.ageCalculatorService = ageCalculatorService;
    }
    
    @Override
    public List<Firestation> getAll() {
        return firestationSpi.getAll();
    }
    
    @Override
    public List<Firestation> getByName(String station) {
        logger.debug("The station parsed: " + station);
        return firestationSpi.getByStation(station);
    }
    
    @Override
    public Firestation getByAddress(String address) {
        logger.debug("The address parsed: " + address);
        return firestationSpi.getByAddress(address);
    }
    
    
    @Override
    public Firestation save(Firestation firestation) {
        logger.debug("The Firestation parsed: " + firestation.getAddress() + " " + firestation.getStation());
        return firestationSpi.save(firestation);
    }
    
    @Override
    public Firestation update(Firestation firestation, String address) {
        Firestation firestationToUpdate = firestationSpi.getByAddress(address);
        
        if(firestation.getStation() != null)
            firestationToUpdate.setStation(firestation.getStation());
        
        logger.debug("The Firestation parsed: " + firestation.getAddress() + " " + firestation.getStation());
        logger.debug("The address parsed: " + address);
        return firestationSpi.update(firestationToUpdate, address);
    }
    
    @Override
    public void delete(Firestation firestation) {
        logger.debug("The Firestation parsed: " + firestation.getAddress() + " " + firestation.getStation());
        firestationSpi.delete(firestation);
    }
    
    /**
     * Return all Person living in an address covered by the firestation number parsed.
     *
     * @param station the number of the firestation, allows the firestation to be retrieved.
     * @return a ListPersonStationNumberDTO
     * @see ListPersonStationNumberDTO
     */
    @Override
    public ListPersonStationNumberDTO getPersonsCoveredByFirestationId(String station) {
        List<Person> personsCoveredByAFirestation = addressCalculatorService.personsCoveredByAFirestation(station);
        logger.debug("personsCoveredByAFirestation.size: " + personsCoveredByAFirestation.size());
        
        List<PersonStationNumberDTO> persons = new ArrayList<>();
        
        personsCoveredByAFirestation.forEach(person -> persons.add(
                new PersonStationNumberDTO(person.getFirstName(), person.getLastName(), person.getAddress(),
                        person.getPhone())));
        
        logger.debug("personsInfosFiltered size: " + persons.size());
        
        int adult = ageCalculatorService.counterOfAdultListFilter(personsCoveredByAFirestation);
        int minor = personsCoveredByAFirestation.size() - adult;
        logger.debug("minor: " + minor + " adult: " + adult);
        
        return new ListPersonStationNumberDTO(persons, adult, minor);
    }
}
