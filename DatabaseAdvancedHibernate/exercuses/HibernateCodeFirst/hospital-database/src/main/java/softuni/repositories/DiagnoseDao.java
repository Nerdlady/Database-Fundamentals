package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Diagnose;

@Repository
public interface DiagnoseDao extends JpaRepository<Diagnose,Long> {
}
