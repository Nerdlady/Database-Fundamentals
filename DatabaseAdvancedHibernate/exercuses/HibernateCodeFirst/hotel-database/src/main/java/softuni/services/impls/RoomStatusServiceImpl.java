package softuni.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.RoomStatus;
import softuni.repositories.RoomStatusDao;
import softuni.services.interfaces.RoomStatusService;

@Service
public class RoomStatusServiceImpl implements RoomStatusService {
    private final RoomStatusDao roomStatusDao;

    @Autowired
    public RoomStatusServiceImpl(RoomStatusDao roomStatusDao) {
        this.roomStatusDao = roomStatusDao;
    }

    @Override
    public void persist(RoomStatus roomStatus) {
        this.roomStatusDao.saveAndFlush(roomStatus);
    }
}
