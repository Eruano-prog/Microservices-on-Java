package JTechLabs.Lab5.APIService.BLL;

import JTechLabs.Lab5.APIService.DLL.HostProducer;
import JTechLabs.Lab5.APIService.DLL.IAuthRepository;
import JTechLabs.Lab5.APIService.DLL.IRequestRepository;
import JTechLabs.Lab5.APIService.Models.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class HostService implements UserDetailsService {
    private final HostProducer hostProducer;
    private final PasswordEncoder encoder;
    private final IAuthRepository authRepository;
    private final IRequestRepository requestRepository;
    private final ObjectMapper objectMapper;

    @Autowired
    public HostService(HostProducer hostProducer, PasswordEncoder encoder, IAuthRepository authRepository, IRequestRepository requestRepository, ObjectMapper objectMapper) {
        this.hostProducer = hostProducer;
        this.encoder = encoder;
        this.authRepository = authRepository;
        this.requestRepository = requestRepository;
        this.objectMapper = objectMapper;
    }

    public void addHost(HostDTO host) throws JsonProcessingException {
        Host convertedHost = host.toHost();
        convertedHost.setPassword(encoder.encode(convertedHost.getPassword()));
        hostProducer.saveHost(convertedHost);
    }

    public void deleteHost(String name) throws JsonProcessingException {
        hostProducer.deleteHost(name);
    }

    public void getHost(Integer requestID, String name) throws JsonProcessingException {
        hostProducer.getHost(requestID, name);
    }

    public void modifyHost(HostDTO host) throws JsonProcessingException {
        Host convertedHost = host.toHost();
        convertedHost.setPassword(encoder.encode(convertedHost.getPassword()));
        hostProducer.putHost(convertedHost);
    }

    public List<Host> checkRequest(Integer requestId) throws JsonProcessingException {
        HostGetRequest request = requestRepository.findByRequestID(requestId);

        return objectMapper.readValue(request.getResult(), new TypeReference<List<Host>>(){});
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Host> host = authRepository.findByName(username);
        return host.map(HostDetails::new)
                .orElseThrow(() -> new UsernameNotFoundException("Host with name " + username + " not found"));
    }
}
