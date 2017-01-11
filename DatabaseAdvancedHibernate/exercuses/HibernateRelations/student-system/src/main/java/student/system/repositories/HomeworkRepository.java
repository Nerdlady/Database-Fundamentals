package student.system.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import student.system.domain.Homework;

@Repository
public interface HomeworkRepository extends JpaRepository<Homework,Long> {
}