package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.StoreLocation;
import softuni.repositories.StoreLocationDao;

import java.util.List;

@Service
public class StoreLocationServiceImpl implements StoreLocationService {
    private final StoreLocationDao storeLocationDao;

    @Autowired
    public StoreLocationServiceImpl(StoreLocationDao storeLocationDao) {
        this.storeLocationDao = storeLocationDao;
    }

    @Override
    public void persist(StoreLocation storeLocation) {
        this.storeLocationDao.saveAndFlush(storeLocation);
    }

    @Override
    public List<StoreLocation> getAll() {
        return this.storeLocationDao.findAll();
    }
}
