package softuni.services.impls;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.BedType;
import softuni.repositories.BedTypeDao;
import softuni.services.interfaces.BedTypeService;

@Service
public class BedTypeServiceImpl implements BedTypeService {

    private final BedTypeDao bedTypeDao;

    @Autowired
    public BedTypeServiceImpl(BedTypeDao bedTypeDao) {
        this.bedTypeDao = bedTypeDao;
    }

    @Override
    public void persist(BedType bedType) {
        this.bedTypeDao.saveAndFlush(bedType);
    }
}
