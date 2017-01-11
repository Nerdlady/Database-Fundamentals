package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.Room;

@Repository
public interface RoomDao extends JpaRepository<Room,Long> {
}
