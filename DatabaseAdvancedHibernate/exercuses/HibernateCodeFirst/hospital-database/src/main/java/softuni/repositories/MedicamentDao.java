package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Medicament;

@Repository
public interface MedicamentDao extends JpaRepository<Medicament,Long> {
}
