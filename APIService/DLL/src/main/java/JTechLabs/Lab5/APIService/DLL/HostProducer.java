package JTechLabs.Lab5.APIService.DLL;

import JTechLabs.Lab5.APIService.Models.Host;
import JTechLabs.Lab5.APIService.Models.HostGetMessage;
import JTechLabs.Lab5.APIService.Models.HostGetRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
public class HostProducer {

    private final ObjectMapper objectMapper;
    private final KafkaTemplate<String, String> kafkaTemplate;

    @Autowired
    public HostProducer(KafkaTemplate<String, String> kafkaTemplate, ObjectMapper objectMapper) {
        this.kafkaTemplate = kafkaTemplate;
        this.objectMapper = objectMapper;
    }

    public void putHost(Host host) throws JsonProcessingException {
        String hostAsMessage = objectMapper.writeValueAsString(host);
        kafkaTemplate.send("host.put", hostAsMessage);
    }

    public void saveHost(Host host) throws JsonProcessingException {
        String hostAsMessage = objectMapper.writeValueAsString(host);
        kafkaTemplate.send("host.save", hostAsMessage);
    }

    public void deleteHost(String hostName) throws JsonProcessingException {
        String hostAsMessage = objectMapper.writeValueAsString(hostName);
        kafkaTemplate.send("host.delete", hostAsMessage);
    }

    public void getHost(Integer requestId, String hostName) throws JsonProcessingException {
        HostGetMessage message = new HostGetMessage(requestId, hostName);
        String hostAsMessage = objectMapper.writeValueAsString(message);
        kafkaTemplate.send("host.get", hostAsMessage);
    }
}