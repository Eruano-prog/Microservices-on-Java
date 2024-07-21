package JTechLabs.Lab5.APIService.DLL;

import JTechLabs.Lab5.APIService.Models.Host;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IAuthRepository extends JpaRepository<Host, Integer> {

    Optional<Host> findByName(String username);
}
