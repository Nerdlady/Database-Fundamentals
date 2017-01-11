package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Occupancy;

@Repository
public interface OccupancyDao extends JpaRepository<Occupancy,Long> {
}
