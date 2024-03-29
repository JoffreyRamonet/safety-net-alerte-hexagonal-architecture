package com.infrastructure.data_reader.data_list;

import com.domain.model.Person;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
/**
 * Class used to deserialize the jsonNode into a list of Person.
 */
@Getter
@Setter
public class PersonsList {
    
    private List<Person> persons = new ArrayList<>();

}
