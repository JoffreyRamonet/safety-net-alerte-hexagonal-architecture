package com.domain.service;

import com.domain.dto.EmailDTO;
import com.domain.dto.ListChildAlertDTO;
import com.domain.dto.ListEmailDTO;
import com.domain.dto.ListFirePersonDTO;
import com.domain.dto.ListPersonsCoveredDTO;
import com.domain.dto.ListPersonsCoveredResponseDTO;
import com.domain.dto.ListPersonsInfoDTO;
import com.domain.dto.ListPhoneAlertDTO;
import com.domain.dto.MedicalrecordDTO;
import com.domain.dto.NamesDTO;
import com.domain.dto.PersonAndMedicalrecordsDTO;
import com.domain.dto.PersonInfosDTO;
import com.domain.dto.PhoneAlertDTO;
import com.domain.model.Firestation;
import com.domain.model.Medicalrecord;
import com.domain.model.Person;
import com.domain.dto.ChildAlertDTO;
import com.domain.api.SpecialsRequestsApi;
import com.domain.ddd.DomainService;
import com.domain.spi.FirestationSpi;
import com.domain.spi.MedicalrecordsSpi;
import com.domain.spi.PersonSpi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to perform SpecialsRequestsService business treatments.
 * Implements SpecialsRequestsApi to send responses to controllers in the infrastructure layer.
 *
 * @see SpecialsRequestsApi
 * <p>
 * Use the FirestationSpi to get data from the infrastructure layer.
 * @see FirestationSpi
 * Use the MedicalrecordsSpi to get data from the infrastructure layer.
 * @see MedicalrecordsSpi
 * Use the PersonSpi to get data from the infrastructure layer.
 * @see PersonSpi
 * <p>
 * Annotated with @DomainService to be considered a Bean by the infrastructure layer.
 * @see DomainService
 */
@DomainService
public class SpecialsRequestsService implements SpecialsRequestsApi {
    
    private final static Logger logger = LoggerFactory.getLogger(SpecialsRequestsService.class);
    
    private final PersonSpi personSpi;
    private final FirestationSpi firestationSpi;
    private final MedicalrecordsSpi medicalrecordsSpi;
    private final AgeCalculatorService ageCalculatorService;
    
    
    public SpecialsRequestsService(PersonSpi personSpi, FirestationSpi firestationSpi,
                                   MedicalrecordsSpi medicalrecordsSpi, AgeCalculatorService ageCalculatorService) {
        this.personSpi = personSpi;
        this.firestationSpi = firestationSpi;
        this.medicalrecordsSpi = medicalrecordsSpi;
        this.ageCalculatorService = ageCalculatorService;
    }
    
    /**
     * Return a list of all children and their family at the address parsed.
     *
     * @param address the attribute parsed to find children.
     * @return a ListChildAlertDTO
     * @see ListChildAlertDTO
     * @see ChildAlertDTO
     */
    @Override
    public ListChildAlertDTO getChildrenAndFamilyAtAddress(String address) {
        List<ChildAlertDTO> childAlertDTOList = new ArrayList<>();
        List<NamesDTO> familyDTOList = new ArrayList<>();
        
        List<Person> personsFindByAddress = personSpi.getByAddress(address);
        List<Person> minorList = ageCalculatorService.adultListFilter(personsFindByAddress);
        List<Person> adultList = personsFindByAddress.stream()
                .filter(person -> !minorList.contains(person))
                .toList();
        
        minorList.forEach(child -> childAlertDTOList.add(new ChildAlertDTO(child.getFirstName(), child.getLastName(),
                ageCalculatorService.ageCalculator(new NamesDTO(child.getFirstName(), child.getLastName())))));
        
        adultList.forEach(person -> familyDTOList.add(new NamesDTO(person.getFirstName(), person.getLastName())));
        
        logger.debug("Address parsed : " + address);
        logger.debug("personsFindByAddress size: " + personsFindByAddress.size());
        logger.debug("minorPersonsList size: " + minorList.size() + " || adultList size: " + adultList.size());
        logger.debug("ChildAlertList size: " + childAlertDTOList.size() + " FamilyList size: " + familyDTOList.size());
        
        return new ListChildAlertDTO(childAlertDTOList, familyDTOList);
    }
    
    /**
     * Return a list of all Person's phone number covered by a station.
     *
     * @param station the attribute allows the firestation to be found.
     * @return a ListPhoneAlertDTO
     * @see ListPhoneAlertDTO
     * @see PhoneAlertDTO
     */
    @Override
    public ListPhoneAlertDTO getPhoneOfAllPersonsCoveredByFirestationNumber(String station) {
        List<Person> persons = new ArrayList<>();
        List<PhoneAlertDTO> phoneAlertList = new ArrayList<>();
        
        List<Firestation> firestations = firestationSpi.getByStation(station);
        
        firestations.forEach(firestation -> persons.addAll(personSpi.getByAddress(firestation.getAddress())));
        
        persons.forEach(person -> phoneAlertList.add(new PhoneAlertDTO(person.getPhone())));
        
        logger.debug("Station parsed : " + station);
        logger.debug("List of firestation with number: " + station + " is: " + firestations.size());
        logger.debug("Person covered list size : " + persons.size());
        logger.debug("PhoneAlertList size : " + phoneAlertList.size());
        
        return new ListPhoneAlertDTO(phoneAlertList);
    }
    
    /**
     * Return a list of all Person and their medicalrecords with their age, and the firestation from an address.
     *
     * @param address the attribute parsed to found all Person and the Firestation.
     * @return a ListFirePersonDTO
     * @see ListFirePersonDTO
     * @see PersonAndMedicalrecordsDTO
     */
    @Override
    public ListFirePersonDTO getPersonsAndTheirMedicalrecordsFromAddressAndTheirFirestation(String address) {
        List<Person> personsByAddressList = personSpi.getByAddress(address);
        
        List<PersonAndMedicalrecordsDTO> personFireAlertList = new ArrayList<>();
        
        personsByAddressList.forEach(person -> personFireAlertList.add(
                new PersonAndMedicalrecordsDTO(person.getFirstName(), person.getLastName(), person.getPhone(),
                        ageCalculatorService.ageCalculator(new NamesDTO(person.getFirstName(), person.getLastName())),
                        medicalrecordDTOGenerator(new NamesDTO(person.getFirstName(), person.getLastName())))));
        
        logger.debug("Address parsed : " + address);
        logger.debug("Persons at this address : " + personsByAddressList.size());
        logger.debug("personFireAlertList size : " + personFireAlertList.size());
        
        return new ListFirePersonDTO(personFireAlertList, firestationSpi.getByAddress(address)
                .getStation());
    }
    
    /**
     * Return a list of all Person and their medicalrecords with their age from a list of Firestation.
     *
     * @param stations the list parsed to calculate all address covered.
     * @return a ListPersonsCoveredResponseDTO
     * @see ListPersonsCoveredResponseDTO
     * @see ListPersonsCoveredDTO
     * @see PersonAndMedicalrecordsDTO
     */
    @Override
    public ListPersonsCoveredResponseDTO getPersonsAndTheirMedicalrecordsFromAFirestationList(List<String> stations) {
        List<ListPersonsCoveredDTO> listPersonsCoveredList = new ArrayList<>();
        List<Firestation> firestations = new ArrayList<>();
        
        stations.forEach(stationId -> firestations.addAll(firestationSpi.getByStation(stationId)));
        
        firestations.forEach(firestation -> {
            List<Person> personsByAddressList = personSpi.getByAddress(firestation.getAddress());
            
            List<PersonAndMedicalrecordsDTO> personAndMedicalrecordsList = new ArrayList<>();
            String address = firestation.getAddress();
            
            personsByAddressList.forEach(person -> personAndMedicalrecordsList.add(
                    new PersonAndMedicalrecordsDTO(person.getFirstName(), person.getLastName(), person.getPhone(),
                            ageCalculatorService.ageCalculator(
                                    new NamesDTO(person.getFirstName(), person.getLastName())),
                            medicalrecordDTOGenerator(new NamesDTO(person.getFirstName(), person.getLastName())))));
            listPersonsCoveredList.add(new ListPersonsCoveredDTO(address, personAndMedicalrecordsList));
        });
        
        logger.debug("Stations parsed : " + stations + " || Number of Firestation : " + firestations.size());
        logger.debug("Number of ListPersonsCoveredDTO : " + listPersonsCoveredList.size());
        
        return new ListPersonsCoveredResponseDTO(listPersonsCoveredList);
    }
    
    /**
     * Return a list of a Person and their family with their age and medicalrecords from a lastName.
     *
     * @param namesDTO the object who contains the lastName.
     * @return a ListPersonsInfoDTO
     * @see ListPersonsInfoDTO
     * @see PersonInfosDTO
     */
    @Override
    public ListPersonsInfoDTO getPersonAndTheirFamily(NamesDTO namesDTO) {
        List<PersonInfosDTO> personsInfoList = new ArrayList<>();
        
        List<Person> family = getFamily(namesDTO);
        
        family.forEach(person -> personsInfoList.add(
                new PersonInfosDTO(person.getFirstName(), person.getLastName(), person.getAddress(),
                        ageCalculatorService.ageCalculator(new NamesDTO(person.getFirstName(), person.getLastName())),
                        person.getEmail(),
                        medicalrecordDTOGenerator(new NamesDTO(person.getFirstName(), person.getLastName())))));
        
        logger.debug("LastName parsed : " + namesDTO.lastName());
        logger.debug("Family size : " + family.size());
        logger.debug("PersonsInfoList size : " + personsInfoList.size());
        
        return new ListPersonsInfoDTO(personsInfoList);
    }
    
    /**
     * Return all email address of all Persons living in the city parsed.
     *
     * @param city the attribute to found all email.
     * @return a ListEmailDTO
     * @see ListEmailDTO
     */
    @Override
    public ListEmailDTO getAllMailOfTheCity(String city) {
        List<EmailDTO> emailList = new ArrayList<>();
        
        personSpi.getAll()
                .stream()
                .filter(person -> person.getCity()
                        .equals(city))
                .toList()
                .forEach(person -> emailList.add(new EmailDTO(person.getEmail())));
        
        logger.debug("City parsed : " + city);
        logger.debug("EmailList size : " + emailList.size());
        
        return new ListEmailDTO(emailList);
    }
    
    /**
     * Return a list of Person who sharing the same lastName.
     *
     * @param namesDTO to parse the lastName.
     * @return a list of Person.
     * @see #getPersonAndTheirFamily
     */
    private List<Person> getFamily(NamesDTO namesDTO) {
        logger.debug("The lastName parsed : " + namesDTO.lastName());
        return personSpi.getAll()
                .stream()
                .filter(person -> person.getLastName()
                        .equals(namesDTO.lastName()))
                .toList();
    }
    
    /**
     * Return a MedicalrecordDTO from a NamesDTO.
     *
     * @param namesDTO the dto to found the medicalrecord.
     * @return the MedicalrecordDTO created.
     * @see #getPersonsAndTheirMedicalrecordsFromAddressAndTheirFirestation
     * @see #getPersonsAndTheirMedicalrecordsFromAFirestationList
     * @see #getPersonAndTheirFamily
     */
    private MedicalrecordDTO medicalrecordDTOGenerator(NamesDTO namesDTO) {
        Medicalrecord medicalrecord = medicalrecordsSpi.getByNames(namesDTO);
        
        logger.debug("Names parsed : " + namesDTO.firstName() + " " + namesDTO.lastName());
        return new MedicalrecordDTO(medicalrecord.getMedications(), medicalrecord.getAllergies());
    }
}
