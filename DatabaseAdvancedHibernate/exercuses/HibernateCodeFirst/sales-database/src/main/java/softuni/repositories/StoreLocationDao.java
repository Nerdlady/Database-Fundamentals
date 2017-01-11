package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.StoreLocation;

@Repository
public interface StoreLocationDao extends JpaRepository<StoreLocation,Long> {
}
