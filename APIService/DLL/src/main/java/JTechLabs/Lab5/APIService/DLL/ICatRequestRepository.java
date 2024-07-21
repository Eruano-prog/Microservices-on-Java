package JTechLabs.Lab5.APIService.DLL;

import JTechLabs.Lab5.APIService.Models.CatGetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICatRequestRepository extends JpaRepository<CatGetRequest, Integer> {
    CatGetRequest findByRequestID(Integer request_id);
}
