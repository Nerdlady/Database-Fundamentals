package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.UserAlbum;
import softuni.entities.UserAlbumId;

@Repository
public interface UserAlbumDao extends JpaRepository<UserAlbum,UserAlbumId> {
}
