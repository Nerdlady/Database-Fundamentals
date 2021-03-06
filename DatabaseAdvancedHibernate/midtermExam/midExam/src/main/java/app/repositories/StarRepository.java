package app.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import app.domain.models.Star;

@Repository
public interface StarRepository extends JpaRepository<Star, Long> {
    Star findByName(String name);
}