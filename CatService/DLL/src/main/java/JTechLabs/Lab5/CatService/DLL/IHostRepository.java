package JTechLabs.Lab5.CatService.DLL;

import JTechLabs.Lab5.CatService.Models.Host;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IHostRepository extends JpaRepository<Host, Integer> {
    Optional<Host> findByName(String name);
}
