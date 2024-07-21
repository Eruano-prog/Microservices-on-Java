package JTechLabs.Lab5.HostService.DLL;

import JTechLabs.Lab5.HostService.Models.HostGetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRequestRepository extends JpaRepository<HostGetRequest, Integer> {
}
