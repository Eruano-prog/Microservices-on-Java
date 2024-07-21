package JTechLabs.Lab5.HostService.BLL;

import JTechLabs.Lab5.HostService.DLL.IHostRepository;
import JTechLabs.Lab5.HostService.DLL.IRequestRepository;
import JTechLabs.Lab5.HostService.Models.Host;
import JTechLabs.Lab5.HostService.Models.HostDTO;
import JTechLabs.Lab5.HostService.Models.HostGetRequest;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HostService {
    private final IHostRepository hostRepository;
    private final IRequestRepository requestRepository;
    private final ObjectMapper mapper;

    @Autowired
    public HostService(IHostRepository hostRepository, IRequestRepository requestRepository, ObjectMapper mapper) {
        this.hostRepository = hostRepository;
        this.requestRepository = requestRepository;
        this.mapper = mapper;
    }

    public HostDTO addHost(HostDTO host){
        Host convertedHost = host.toHost();

        Host savedHost = hostRepository.save(convertedHost);

        return savedHost.toDTO();
    }

    public void deleteHost(String name){
        Host host = hostRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Host with name " + name + " not found"));

        hostRepository.delete(host);
    }

    public void getHost(Integer requestID, String name) throws JsonProcessingException {
        Host host = hostRepository.findByName(name)
                .orElseThrow(() -> new EntityNotFoundException("Host with name " + name + " not found"));

        List<Host> hosts = new ArrayList<>();
        hosts.add(host);

        String results = mapper.writeValueAsString(hosts);
        HostGetRequest request = new HostGetRequest(null, requestID, results);
        requestRepository.save(request);
    }

    public HostDTO modifyHost(HostDTO host){
        Host dbHost = hostRepository.findByName(host.getName())
                .orElseThrow(() -> new EntityNotFoundException("Host with name " + host.getName() + " not found"));

        dbHost.setFromDTO(host);

        Host savedHost = hostRepository.save(dbHost);

        return savedHost.toDTO();
    }
}
