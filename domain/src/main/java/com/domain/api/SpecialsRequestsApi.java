package com.domain.api;



import com.domain.dto.ListChildAlertDTO;
import com.domain.dto.ListEmailDTO;
import com.domain.dto.ListFirePersonDTO;
import com.domain.dto.ListPersonsCoveredResponseDTO;
import com.domain.dto.ListPersonsInfoDTO;
import com.domain.dto.ListPhoneAlertDTO;
import com.domain.dto.NamesDTO;

import java.util.List;

/**
 * Interface to perform complex requests from controllers to the domain for the Medicalrecord.
 *
 * @see com.domain.service.SpecialsRequestsService
 */
public interface SpecialsRequestsApi {
    /**
     * Return a list of all children and their family at the address parsed.
     *
     * @param address the attribute parsed to find children.
     * @return a ListChildAlertDTO
     * @see ListChildAlertDTO
     * @see com.domain.dto.ChildAlertDTO
     */
    ListChildAlertDTO getChildrenAndFamilyAtAddress(String address);
    
    /**
     * Return a list of all Person's phone number covered by a station.
     *
     * @param station the attribute allows the firestation to be found.
     * @return a ListPhoneAlertDTO
     * @see ListPhoneAlertDTO
     * @see com.domain.dto.PhoneAlertDTO
     */
    ListPhoneAlertDTO getPhoneOfAllPersonsCoveredByFirestationNumber(String station);
    
    /**
     * Return a list of all Person and their medicalrecords with their age, and the firestation from an address.
     *
     * @param address the attribute parsed to found all Person and the Firestation.
     * @return a ListFirePersonDTO
     * @see ListFirePersonDTO
     * @see com.domain.dto.PersonAndMedicalrecordsDTO
     */
    ListFirePersonDTO getPersonsAndTheirMedicalrecordsFromAddressAndTheirFirestation(String address);
    
    /**
     * Return a list of all Person and their medicalrecords with their age from a list of Firestation.
     *
     * @param stations the list parsed to calculate all address covered.
     * @return a ListPersonsCoveredResponseDTO
     * @see ListPersonsCoveredResponseDTO
     * @see com.domain.dto.ListPersonsCoveredDTO
     * @see com.domain.dto.PersonAndMedicalrecordsDTO
     */
    ListPersonsCoveredResponseDTO getPersonsAndTheirMedicalrecordsFromAFirestationList(List<String> stations);
    
    /**
     * Return a list of a Person and their family with their age and medicalrecords from a lastName.
     *
     * @param namesDTO the object who contains the lastName.
     * @return a ListPersonsInfoDTO
     * @see ListPersonsInfoDTO
     * @see com.domain.dto.PersonInfosDTO
     */
    ListPersonsInfoDTO getPersonAndTheirFamily(NamesDTO namesDTO);
    
    /**
     * Return all email address of all Persons living in the city parsed.
     *
     * @param city the attribute to found all email.
     * @return a ListEmailDTO
     * @see ListEmailDTO
     */
    ListEmailDTO getAllMailOfTheCity(String city);
}
