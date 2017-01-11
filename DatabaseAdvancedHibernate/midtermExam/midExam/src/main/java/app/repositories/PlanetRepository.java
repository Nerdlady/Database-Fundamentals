package app.repositories;

import app.domain.models.Planet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanetRepository extends JpaRepository<Planet, Long> {
    Planet findByName(String name);

    List<Planet> findAllByOrOriginalAnomalyIsNull();
}