package com.domain.service;

import com.domain.ddd.DomainService;
import com.domain.model.Person;
import com.domain.model.Firestation;
import com.domain.spi.FirestationSpi;
import com.domain.spi.PersonSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * Class to perform address calculations.
 *
 * @see FirestationService
 * <p>
 * Annotated with @DomainService to be considered a Bean by the infrastructure layer.
 * @see DomainService
 */
@DomainService
public class AddressCalculatorService {
    private final static Logger logger = LoggerFactory.getLogger(AddressCalculatorService.class);
    private final FirestationSpi firestationSpi;
    private final PersonSpi personSpi;
    
    public AddressCalculatorService(FirestationSpi firestationSpi, PersonSpi personSpi) {
        this.firestationSpi = firestationSpi;
        this.personSpi = personSpi;
    }
    
    /**
     * Return a list of person covered by a Firestation.
     *
     * @param station the attributes to found address from the station parsed.
     * @return a list of Person.
     * @see FirestationService#getPersonsCoveredByFirestationId
     */
    public List<Person> personsCoveredByAFirestation(String station) {
        logger.debug("The station parsed: " + station);
        return personSpi.getAll()
                .stream()
                .filter(person -> firestationSpi.getByStation(station)
                        .stream()
                        .map(Firestation::getAddress)
                        .toList()
                        .contains(person.getAddress()))
                .toList();
    }
}
