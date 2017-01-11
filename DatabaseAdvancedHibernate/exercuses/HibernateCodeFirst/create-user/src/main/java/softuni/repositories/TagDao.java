package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Tag;

@Repository
public interface TagDao extends JpaRepository<Tag,Long> {
}
