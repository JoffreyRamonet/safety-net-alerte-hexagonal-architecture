package com.infrastructure.data_reader;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jsoniter.JsonIterator;
import com.infrastructure.data_reader.data_list.FirestationsList;
import com.infrastructure.data_reader.data_list.MedicalrecordsList;
import com.infrastructure.data_reader.data_list.PersonsList;
import com.domain.model.Firestation;
import com.domain.model.Medicalrecord;
import com.domain.model.Person;
import lombok.extern.slf4j.Slf4j;
import org.springframework.asm.TypeReference;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/**
 * The Json reader class.
 * The filepath is static so the application run with this default path to the json file.
 * For the test cycle, a setFilePath is available to load a dataStub.
 */
@Slf4j
public class JsonReader {
    
    
    private static String filepath = "/data.json";
    
    public static void setFilepath(String filepath) {
        JsonReader.filepath = filepath;
    }
    
    /**
     * Method to read the JsonFile located by the filepath.
     *
     * @return a JsonNode of the jsonFile.
     */
    private static JsonNode jsonReader() {
        
        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode json;
        
        try(InputStream inputStream = TypeReference.class.getResourceAsStream(filepath)) {
            json = objectMapper.readValue(inputStream, JsonNode.class);
        } catch(IOException e) {
            log.error("Failed to create the jsonNode in the jsonReader method");
            throw new RuntimeException("Failed to load the file.json", e);
        }
        log.debug("Creation of the jsonNode");
        return json;
    }
    
    /**
     * Method to map the person json array of the json file into a List of the model Person.
     *
     * @return a list of Person.
     * @see com.infrastructure.data_reader.repository.PersonRepository
     */
    public static List<Person> personsList() {
        log.info(filepath);
        JsonNode jsonNode = jsonReader();
        PersonsList deserialize = JsonIterator.deserialize(jsonNode.toString(), PersonsList.class);
        log.debug("Creation of the personList");
        return deserialize.getPersons();
    }
    
    /**
     * Method to map the firestation json array of the json file into a List of the model Firestation.
     *
     * @return a list of Firestation.
     * @see com.infrastructure.data_reader.repository.FirestationRepository
     */
    public static List<Firestation> firestationsList() {
        
        JsonNode jsonNode = jsonReader();
        FirestationsList deserialize = JsonIterator.deserialize(jsonNode.toString(), FirestationsList.class);
        log.debug("Creation of the firestationList");
        return deserialize.getFirestations();
    }
    
    /**
     * Method to map the medicalrecord json array of the json file into a List of the model Medicalrecord.
     *
     * @return a list of Medicalrecord.
     * @see com.infrastructure.data_reader.repository.MedicalrecordRepository
     */
    public static List<Medicalrecord> medicalrecordsList() {
        
        JsonNode jsonNode = jsonReader();
        MedicalrecordsList deserialize = JsonIterator.deserialize(jsonNode.toString(), MedicalrecordsList.class);
        log.debug("Creation of the medicalrecordsList");
        return deserialize.getMedicalrecords();
    }
}
