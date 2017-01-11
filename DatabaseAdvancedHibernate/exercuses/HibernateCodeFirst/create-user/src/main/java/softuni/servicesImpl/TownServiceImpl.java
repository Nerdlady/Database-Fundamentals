package softuni.servicesImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Town;
import softuni.repositories.TownDao;
import softuni.services.TownService;

@Service
public class TownServiceImpl implements TownService {

    private final TownDao townDao;

    @Autowired
    public TownServiceImpl(TownDao townDao) {
        this.townDao = townDao;
    }

    @Override
    public void persist(Town town) {
        this.townDao.saveAndFlush(town);
    }
}
