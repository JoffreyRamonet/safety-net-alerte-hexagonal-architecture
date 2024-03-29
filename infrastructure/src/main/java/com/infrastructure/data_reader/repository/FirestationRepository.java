package com.infrastructure.data_reader.repository;

import com.infrastructure.data_reader.JsonReader;
import com.domain.model.Firestation;
import com.domain.spi.FirestationSpi;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository of the model Firestation.
 * Implement the FirestationSpi to give data from the JsonFile to the domain.
 * Annotated with @Primary cause of repositories of the JsonReader client are the default choice.
 *
 * @see Primary
 * @see FirestationSpi
 * @see JsonReader
 */
@Slf4j
@Repository
@Primary
public class FirestationRepository implements FirestationSpi {
    
    /**
     * Call the JsonReader to create an InMemory list of Firestation.
     */
    private final List<Firestation> firestations = JsonReader.firestationsList();
    
    /**
     * cRud method - read all.
     *
     * @return a list of all Firestation.
     */
    @Override
    public List<Firestation> getAll() {
        return firestations;
    }
    
    /**
     * cRud method - read  by station.
     *
     * @param station the attribute to found Firestations.
     * @return a list of Firestation with the station parsed.
     */
    @Override
    public List<Firestation> getByStation(String station) {
        log.debug("The station parsed: " + station);
        return firestations.stream()
                .filter(firestation -> firestation.getStation()
                        .equals(station))
                .toList();
    }
    
    /**
     * cRud method - read  by address.
     *
     * @param address the attribute to found Firestations.
     * @return a Firestation with the address parsed.
     */
    @Override
    public Firestation getByAddress(String address) {
        log.debug("The address parsed: " + address);
        return firestations.stream()
                .filter(firestation -> firestation.getAddress()
                        .equals(address))
                .toList()
                .get(0);
    }
    
    /**
     * Crud method - create a new Firestation.
     * CAUTION - the Firestation is saved in memory and not writing in the JsonFile!
     *
     * @param firestation Firestation parsed to be saved.
     * @return the Firestation saved.
     */
    @Override
    public Firestation save(Firestation firestation) {
        log.debug("The Firestation parsed: " + firestation.getAddress() + " " + firestation.getStation());
        firestations.add(firestation);
        return firestation;
    }
    
    /**
     * crUd method - update by address.
     * CAUTION - the Firestation is updated in memory and not updated in the JsonFile!
     *
     * @param firestation firestation with attributes have to be modified.
     * @param address     the attribute to found Firestations.
     * @return the Firestation object modified.
     */
    @Override
    public Firestation update(Firestation firestation, String address) {
        for(int i = 0; i < firestations.size(); i++) {
            if(firestations.get(i)
                    .getAddress()
                    .equals(address))
                firestations.set(i, firestation);
        }
        log.debug("The Firestation parsed: " + firestation.getAddress() + " " + firestation.getStation());
        log.debug("The address parsed: " + address);
        return firestation;
    }
    
    /**
     * cruD delete
     * CAUTION - the Firestation is deleted in memory and not deleted in the JsonFile!
     *
     * @param firestation the firestation to delete.
     */
    @Override
    public void delete(Firestation firestation) {
        log.debug("The Firestation parsed: " + firestation.getAddress() + " " + firestation.getStation());
        firestations.removeIf(f -> f.getAddress()
                .equals(firestation.getAddress()) && f.getStation()
                .equals(firestation.getStation()));
    }
}
