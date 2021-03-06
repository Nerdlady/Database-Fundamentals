package student.system.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import student.system.domain.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student,Long> {
}