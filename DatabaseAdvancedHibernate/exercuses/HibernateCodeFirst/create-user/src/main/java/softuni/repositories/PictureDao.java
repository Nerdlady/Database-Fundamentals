package softuni.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import softuni.entities.Picture;

@Repository
public interface PictureDao extends JpaRepository<Picture,Long> {
}