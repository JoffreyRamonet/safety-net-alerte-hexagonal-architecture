package com.domain.model;

/**
 * Used to store attributes of a Firestation.
 * They're no direct relation between Person and Firestation.
 * Person and Firestation juste share the same attribute address.
 *
 * @see com.domain.api.FirestationApi
 * @see com.domain.spi.FirestationSpi
 * @see com.domain.service.FirestationService
 * @see com.domain.service.SpecialsRequestsService
 */
public class Firestation {
    
    private String address;
    private String station;
    
    public Firestation(String address, String station) {
        this.address = address;
        this.station = station;
    }
    
    public Firestation() {
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    
    public String getStation() {
        return station;
    }
    
    public void setStation(String station) {
        this.station = station;
    }
}
