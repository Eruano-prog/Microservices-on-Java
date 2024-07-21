package JTechLabs.Lab5.CatService.DLL;

import JTechLabs.Lab5.CatService.Models.CatGetRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRequestRepository extends JpaRepository<CatGetRequest, Integer> {
}
