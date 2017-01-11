package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Employee;

@Repository
public interface EmployeeDao extends JpaRepository<Employee,Long> {
}
