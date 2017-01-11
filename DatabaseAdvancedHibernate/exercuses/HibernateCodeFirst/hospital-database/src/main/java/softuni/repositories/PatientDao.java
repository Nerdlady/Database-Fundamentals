package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Patient;

@Repository
public interface PatientDao extends JpaRepository<Patient,Long> {
}
