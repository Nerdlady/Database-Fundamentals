package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.BedType;

@Repository
public interface BedTypeDao extends JpaRepository<BedType,String> {
}
