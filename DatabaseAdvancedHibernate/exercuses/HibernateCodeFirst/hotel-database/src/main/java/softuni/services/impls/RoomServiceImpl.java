package softuni.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Room;
import softuni.repositories.RoomDao;
import softuni.services.interfaces.RoomService;

@Service
public class RoomServiceImpl implements RoomService {
    private final RoomDao roomDao;

    @Autowired
    public RoomServiceImpl(RoomDao roomDao) {
        this.roomDao = roomDao;
    }

    @Override
    public void persist(Room room) {
        this.roomDao.saveAndFlush(room);
    }
}
