package com.domain.service;

import com.domain.ddd.DomainService;
import com.domain.dto.NamesDTO;
import com.domain.model.Medicalrecord;
import com.domain.model.Person;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Class to perform age calculations.
 *
 * @see FirestationService
 * @see SpecialsRequestsService
 * <p>
 * Annotated with @DomainService to be considered a Bean by the infrastructure layer.
 * @see DomainService
 */
@DomainService
public class AgeCalculatorService {
    
    private final static Logger logger = LoggerFactory.getLogger(AgeCalculatorService.class);
    private final MedicalrecordService medicalrecordService;
    
    public AgeCalculatorService(MedicalrecordService medicalrecordService) {
        this.medicalrecordService = medicalrecordService;
    }
    
    /**
     * Return the number of adult(+18) from a List of person.
     *
     * @param personsListToFilter the list of Person parsed.
     * @return an int of at least 0.
     * @see FirestationService#getPersonsCoveredByFirestationId
     */
    public int counterOfAdultListFilter(List<Person> personsListToFilter) {
        
        List<Medicalrecord> medicalrecordsListToFilter = getMedicalrecordFromPersonList(personsListToFilter);
        
        int major = 0;
        for(Medicalrecord medicalrecord : medicalrecordsListToFilter) {
            if(adultFiltrer(medicalrecord)) {
                major += 1;
            }
        }
        
        logger.debug("The person list size parsed: " + personsListToFilter.size());
        logger.debug("The medicalrecord list size found: " + medicalrecordsListToFilter.size());
        
        return major;
    }
    
    /**
     * Return a list of minor (-18) from a list of Person.
     *
     * @param personsListToFilter the list of Person parse.
     * @return a list of Person.
     * @see SpecialsRequestsService#getChildrenAndFamilyAtAddress
     */
    public List<Person> adultListFilter(List<Person> personsListToFilter) {
        
        List<Medicalrecord> minorMedicalrecords = getMedicalrecordFromPersonList(personsListToFilter).stream()
                .filter(medicalrecord -> !adultFiltrer(medicalrecord))
                .toList();
        
        Map<String, String> minorMap = minorMedicalrecords.stream()
                .collect(Collectors.toMap(Medicalrecord::getFirstName, Medicalrecord::getLastName));
        
        logger.debug("The person list size parsed: " + personsListToFilter.size());
        logger.debug("The minor medicalrecord list size found: " + minorMedicalrecords.size());
        
        return personsListToFilter.stream()
                .filter(person -> minorMap.containsKey(person.getFirstName()) &&
                        minorMap.containsValue(person.getLastName()))
                .toList();
    }
    
    /**
     * Return an int representing the age of a Person from a NamesDTO.
     *
     * @param namesDTO contains the attributes to find th Person.
     * @return an int of at least 0.
     * @see SpecialsRequestsService#getChildrenAndFamilyAtAddress
     * @see SpecialsRequestsService#getPersonsAndTheirMedicalrecordsFromAddressAndTheirFirestation
     * @see SpecialsRequestsService#getPersonAndTheirFamily
     */
    public int ageCalculator(NamesDTO namesDTO) {
        List<Medicalrecord> medicalrecordsList = medicalrecordService.getAll();
        int age = 0;
        for(Medicalrecord medicalrecord : medicalrecordsList) {
            if(medicalrecord.getFirstName()
                    .equals(namesDTO.firstName()) && medicalrecord.getLastName()
                    .equals(namesDTO.lastName())) {
                DateTimeFormatter format = DateTimeFormatter.ofPattern("MM/dd/yyyy");
                String stringFormat = "MM/dd/yyyy";
                String now = new SimpleDateFormat(stringFormat).format(new Date());
                LocalDate birthdate = LocalDate.parse(medicalrecord.getBirthdate(), format);
                LocalDate current = LocalDate.parse(now, format);
                Period period = Period.between(birthdate, current);
                age = period.getYears();
            }
        }
        logger.debug("Person to calculate the age: " + namesDTO.firstName() + " " + namesDTO.lastName());
        logger.debug("Age calculate: " + age);
        return age;
    }
    
    /**
     * Return a list of Medicalrecord from a list of Person.
     *
     * @param persons the list of Person parsed.
     * @return a list of Medicalrecord.
     * @see #counterOfAdultListFilter
     * @see #adultListFilter
     */
    private List<Medicalrecord> getMedicalrecordFromPersonList(List<Person> persons) {
        
        Map<String, String> map = persons.stream()
                .collect(Collectors.toMap(Person::getFirstName, Person::getLastName));
        
        logger.debug("List of Person parsed size: " + persons.size());
        
        return medicalrecordService.getAll()
                .stream()
                .filter(medicalrecord -> map.containsKey(medicalrecord.getFirstName()) &&
                        map.containsValue(medicalrecord.getLastName()))
                .toList();
    }
    
    /**
     * Reformat a birthdate parsed.
     * Reformat required to filter adult from a list of Person.
     * From "ddd/MM/yyyy to yyyy/MM/dd.
     * And apply a regex to remove slash and get a String with the format yyyyMMdd.
     *
     * @param birthdate the String representing the birthdate.
     * @return a String.
     * @see #adultFiltrer
     */
    private String birthdateFormater(String birthdate) {
        final String OLD_FORMAT = "ddd/MM/yyyy";
        final String NEW_FORMAT = "yyyy/MM/dd";
        
        String formatedBirthdate;
        try {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat(OLD_FORMAT);
            Date date = simpleDateFormat.parse(birthdate);
            simpleDateFormat.applyPattern(NEW_FORMAT);
            formatedBirthdate = simpleDateFormat.format(date);
            formatedBirthdate = formatedBirthdate.replaceAll("/", "");
        } catch(ParseException e) {
            throw new RuntimeException("Error in the birthdate format process, birthdate: " + birthdate);
        }
        
        logger.debug("Birthdate parsed: " + birthdate);
        logger.debug("Birthdate reformated: " + formatedBirthdate);
        return formatedBirthdate;
    }
    
    /**
     * Return true if the Person is major from a Medicalrecord.
     *
     * @param medicalrecord
     * @return a Boolean
     * @see #counterOfAdultListFilter
     * @see #adultListFilter
     */
    private Boolean adultFiltrer(Medicalrecord medicalrecord) {
        boolean major = false;
        
        int toDay = Integer.parseInt(LocalDate.now()
                .toString()
                .replaceAll("-", ""));
        int birthdate = Integer.parseInt(birthdateFormater(medicalrecord.getBirthdate()));
        int age = toDay - birthdate;
        
        if(age < 0) {
            throw new RuntimeException("The birthdate can't be later than current date");
        }
        
        if(age > 180000) {
            major = true;
        }
        logger.debug("Medicalrecord parsed: " + medicalrecord);
        logger.debug("Birthdate formated: " + birthdate);
        logger.debug("Is major: " + major);
        return major;
    }
}
