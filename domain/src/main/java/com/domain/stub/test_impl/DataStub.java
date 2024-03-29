package com.domain.stub.test_impl;


import com.domain.ddd.Stub;
import com.domain.model.Firestation;
import com.domain.model.Medicalrecord;
import com.domain.model.Person;

import java.util.ArrayList;
import java.util.List;

@Stub
public final class DataStub {
    
    private final List<Person> persons = new ArrayList<>(
            List.of(new Person("John", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-651", "jaboyd@email.com"),
                    new Person("Jacob", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6513", "drk@email.com"),
                    new Person("Tenley", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512", "tenz@email.com"),
                    new Person("Roger", "Boyd", "1509 Culver St", "Culver", "97451", "841-874-6512",
                            "jaboyd@email.com"),
                    new Person("Jonanathan", "Marrack", "29 15th St", "Culver", "97451", "841-874-6513",
                            "drk@email.com")));
    private final List<Firestation> firestations = new ArrayList<>(
            List.of(new Firestation("1509 Culver St", "3"), new Firestation("29 15th St", "2"),
                    new Firestation("834 Binoc Ave", "3")));
    
    private final List<Medicalrecord> medicalrecords = new ArrayList<>(
            List.of(new Medicalrecord("John", "Boyd", "03/06/1984",
                            new ArrayList<>(List.of("aznol:350mg", "hydrapermazol:100mg")),
                            new ArrayList<>(List.of("nillacilan"))),
                    new Medicalrecord("Jacob", "Boyd", "03/06/1989",
                            new ArrayList<>(List.of("pharmacol:5000mg", "terazine:10mg", "noznazol:250mg")), new ArrayList<>()),
                    new Medicalrecord("Tenley", "Boyd", "02/18/2012", new ArrayList<>(),
                            new ArrayList<>(List.of("peanut"))),
                    new Medicalrecord("Roger", "Boyd", "09/06/2017", new ArrayList<>(), new ArrayList<>()),
                    new Medicalrecord("Jonanathan", "Marrack", "01/03/1989", new ArrayList<>(), new ArrayList<>())));
    
    public List<Person> persons() {
        return persons;
    }
    
    public List<Firestation> firestations() {
        return firestations;
    }
    
    public List<Medicalrecord> medicalrecords() {
        return medicalrecords;
    }
}
