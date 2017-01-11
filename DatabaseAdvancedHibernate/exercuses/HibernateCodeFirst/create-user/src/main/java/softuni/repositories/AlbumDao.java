package softuni.repositories;

import org.springframework.stereotype.Repository;
import org.springframework.data.jpa.repository.JpaRepository;
import softuni.entities.Album;

@Repository
public interface AlbumDao extends JpaRepository<Album,Long> {
}