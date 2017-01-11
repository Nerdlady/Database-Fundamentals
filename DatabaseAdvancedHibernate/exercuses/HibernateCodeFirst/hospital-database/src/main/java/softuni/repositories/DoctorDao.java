package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Doctor;

@Repository
public interface DoctorDao  extends JpaRepository<Doctor,Long>{
}
