package com.infrastructure.data_reader.data_list;

import com.domain.model.Firestation;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

/**
 * Class used to deserialize the jsonNode into a list of Firestation.
 */
@Getter
@Setter
public class FirestationsList {
    
    private List<Firestation> firestations = new ArrayList<>();
    
}
