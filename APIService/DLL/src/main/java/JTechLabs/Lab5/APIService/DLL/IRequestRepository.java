package JTechLabs.Lab5.APIService.DLL;

import JTechLabs.Lab5.APIService.Models.HostGetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRequestRepository extends JpaRepository<HostGetRequest, Integer> {
    HostGetRequest findByRequestID(Integer request_id);
}
