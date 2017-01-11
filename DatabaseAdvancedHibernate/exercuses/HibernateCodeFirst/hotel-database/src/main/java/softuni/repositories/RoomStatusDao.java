package softuni.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.entities.RoomStatus;

@Repository
public interface RoomStatusDao extends JpaRepository<RoomStatus,Long> {
}
