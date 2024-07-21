package JTechLabs.Lab5.HostService.Controller;

import JTechLabs.Lab5.HostService.Models.HostDTO;
import JTechLabs.Lab5.HostService.Models.HostGetMessage;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import JTechLabs.Lab5.HostService.BLL.HostService;

@Component
public class HostListener {

    private final ObjectMapper mapper;
    private final HostService hostService;

    @Autowired
    public HostListener(ObjectMapper mapper, HostService hostService) {
        this.mapper = mapper;
        this.hostService = hostService;
    }

    @KafkaListener(topics = "host.save")
    public void saveHostPoint(String  message){
        HostDTO host;
        try {
            host = mapper.readValue(message, HostDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        hostService.addHost(host);
    }

    @KafkaListener(topics = "host.delete")
    public void deleteHostPoint(String  message){
        String hostName;
        try {
            hostName = mapper.readValue(message, String.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        hostService.deleteHost(hostName);
    }

    @KafkaListener(topics = "host.put")
    public void putHostPoint(String  message){
        HostDTO host;
        try {
            host = mapper.readValue(message, HostDTO.class);
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
            return;
        }

        hostService.modifyHost(host);
    }

    @KafkaListener(topics = "host.get")
    public void getHostPoint(String  message){
        HostGetMessage getMessage;
        try {
            getMessage = mapper.readValue(message, HostGetMessage.class);
            hostService.getHost(getMessage.getRequestID(), getMessage.getHostName());
        } catch (JsonProcessingException e) {
            System.out.println(e.getMessage());
        }
    }
}
