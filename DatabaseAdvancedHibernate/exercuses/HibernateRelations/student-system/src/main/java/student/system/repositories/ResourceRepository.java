package student.system.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import student.system.domain.Resource;

@Repository
public interface ResourceRepository extends JpaRepository<Resource,Long> {
}