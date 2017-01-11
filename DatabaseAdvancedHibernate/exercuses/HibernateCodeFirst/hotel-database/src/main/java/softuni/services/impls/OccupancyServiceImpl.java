package softuni.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Occupancy;
import softuni.repositories.OccupancyDao;
import softuni.services.interfaces.OccupancyService;

@Service
public class OccupancyServiceImpl implements OccupancyService {

    private final OccupancyDao occupancyDao;

    @Autowired
    public OccupancyServiceImpl(OccupancyDao occupancyDao) {
        this.occupancyDao = occupancyDao;
    }

    @Override
    public void persist(Occupancy occupancy) {
        this.occupancyDao.saveAndFlush(occupancy);
    }
}
