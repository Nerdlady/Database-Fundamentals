package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Visitation;

@Repository
public interface VisitationDao extends JpaRepository<Visitation,Long> {
}
