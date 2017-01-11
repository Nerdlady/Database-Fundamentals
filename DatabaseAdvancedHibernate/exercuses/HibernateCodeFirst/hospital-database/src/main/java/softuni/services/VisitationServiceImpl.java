package softuni.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import softuni.entities.Visitation;
import softuni.repositories.VisitationDao;

@Service
public class VisitationServiceImpl implements VisitationService {
    private final VisitationDao visitationDao;

    @Autowired
    public VisitationServiceImpl(VisitationDao visitationDao) {
        this.visitationDao = visitationDao;
    }

    @Override
    public void persist(Visitation visitation) {
        this.visitationDao.saveAndFlush(visitation);
    }
}
