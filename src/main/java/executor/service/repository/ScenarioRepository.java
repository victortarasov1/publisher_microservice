package executor.service.repository;

import executor.service.model.Scenario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ScenarioRepository extends JpaRepository<Scenario, String> {
    Optional<Scenario> findById(String id);
}
