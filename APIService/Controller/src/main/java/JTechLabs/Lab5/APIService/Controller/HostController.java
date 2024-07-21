package JTechLabs.Lab5.APIService.Controller;

import JTechLabs.Lab5.APIService.BLL.HostService;
import JTechLabs.Lab5.APIService.Models.Host;
import JTechLabs.Lab5.APIService.Models.HostDTO;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/host")
@PreAuthorize("hasAuthority('ROLE_ADMIN')")
public class HostController {
    public HostService hostService;

    @Autowired
    public HostController(HostService hostService) {
        this.hostService = hostService;
    }

    @GetMapping(value = "/{name}")
    public ResponseEntity<String> getHost(@PathVariable String name){
        Random random = new Random();
        Integer requestID = random.nextInt(10000);

        try {
            hostService.getHost(requestID, name);
        } catch (JsonProcessingException e) {
            ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Parsing error");
        }


        return ResponseEntity.ok("/host/request/"+requestID);
    }

    @GetMapping(value="/request/{requestID}")
    public ResponseEntity<List<Host>> getRequest(@PathVariable Integer requestID){
        List<Host> hosts = null;
        try {
            hosts = hostService.checkRequest(requestID);
        } catch (JsonProcessingException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
        return ResponseEntity.ok(hosts);
    }

    @PostMapping
    public ResponseEntity<HostDTO> addHost(@RequestBody HostDTO host) throws JsonProcessingException {
        hostService.addHost(host);

        return ResponseEntity.ok(host);
    }

    @PutMapping()
    public ResponseEntity<HostDTO> modifyHost(@RequestBody HostDTO host) throws JsonProcessingException {
        hostService.modifyHost(host);

        return ResponseEntity.ok(host);
    }

    @DeleteMapping("/{name}")
    public ResponseEntity<String> deleteHost(@PathVariable String name) throws JsonProcessingException {
        hostService.deleteHost(name);

        return ResponseEntity.ok(name);
    }
}
