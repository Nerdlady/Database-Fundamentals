package student.system.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import student.system.domain.License;

@Repository
public interface LicenseRepository extends JpaRepository<License,Long> {
}