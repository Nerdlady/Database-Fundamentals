package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.RoomType;

@Repository
public interface RoomTypeDao extends JpaRepository<RoomType,String> {
}
