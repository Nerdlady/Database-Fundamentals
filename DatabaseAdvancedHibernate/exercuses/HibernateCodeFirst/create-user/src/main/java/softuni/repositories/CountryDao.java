package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Country;

@Repository
public interface CountryDao extends JpaRepository<Country,Long> {
}
