package app.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.models.Anomaly;

@Repository
public interface AnomalyRepository extends JpaRepository<Anomaly, Long> {

    @Query("SELECT a FROM Anomaly AS a WHERE a.victims.size = (SELECT MAX(a2.victims.size) FROM Anomaly AS a2)")
    Anomaly findByMaxVictims();
}