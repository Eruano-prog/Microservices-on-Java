package JTechLabs.Lab5.APIService.DLL;

import JTechLabs.Lab5.APIService.Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class CatProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public CatProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void putCat(String hostName, CatDTO cat) throws JsonProcessingException {
        CatWithHostnameMessage message = new CatWithHostnameMessage(hostName, cat);
        String catAsMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send("cat.put", catAsMessage);
    }

    public void saveCat(String hostName, CatDTO cat) throws JsonProcessingException {
        CatWithHostnameMessage message = new CatWithHostnameMessage(hostName, cat);
        String catAsMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send("cat.save", catAsMessage);
    }

    public void deleteCat(String hostName, String name) throws JsonProcessingException {
        CatnameWithHostnameMessage message = new CatnameWithHostnameMessage(hostName, name);
        String catAsMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send("cat.delete", catAsMessage);
    }

    public void getCat(String hostName, String name, Integer requestID) throws JsonProcessingException {
        CatnameWithHostnameGetMessage message = new CatnameWithHostnameGetMessage(hostName, name, requestID);
        String catAsMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send("cat.get", catAsMessage);
    }

    public void getCatByColor(String name, catColor color, Integer requestID) throws JsonProcessingException {
        HostnameWithCatColorGetMessage message = new HostnameWithCatColorGetMessage(name, color, requestID);
        String catAsMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send("cat.getByColor", catAsMessage);
    }
}