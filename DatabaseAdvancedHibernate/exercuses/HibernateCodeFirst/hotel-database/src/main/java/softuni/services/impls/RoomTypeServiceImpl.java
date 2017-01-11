package softuni.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.RoomType;
import softuni.repositories.RoomTypeDao;
import softuni.services.interfaces.RoomTypeService;

@Service
public class RoomTypeServiceImpl implements RoomTypeService {
    private final RoomTypeDao roomTypeDao;

    @Autowired
    public RoomTypeServiceImpl(RoomTypeDao roomTypeDao) {
        this.roomTypeDao = roomTypeDao;
    }

    @Override
    public void persist(RoomType roomType) {
        this.roomTypeDao.saveAndFlush(roomType);
    }
}
